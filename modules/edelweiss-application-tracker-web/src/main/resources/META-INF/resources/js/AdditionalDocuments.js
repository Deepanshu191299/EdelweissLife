import React, { useEffect, useState } from "react";
import {
  callGet,
  callPost,
  showFailure,
  showSuccess,
} from "./utilities/utility";

const CASE_1_STRING = "CAN SHOW AND UPLOAD";
const CASE_2_STRING = "SHOW ON SCREEN BUT CAN NOT BE UPLOADED FROM SCREEN";

const FILE_TYPES = ["application/pdf", "image/png", "image/jpg", "image/jpeg"];

const DEFAULT_ERROS = {
  required: Liferay.Language.get("label-please-upload-document"),
  fileSize: Liferay.Language.get(
    "label-at-please-upload-document-less-than-5-mb"
  ),
  default: Liferay.Language.get(
    "label-at-service-is-temproraly-unavailable-please-try-after-some-time"
  ),
  fileTypes: Liferay.Language.get("label-file-allowed-types"),
  documentType: Liferay.Language.get("label-please-select-dropdown-value"),
};

const CASE_3_STRING = "DO NOT SHOW ON SCREEN";

const AdditionalDocuments = ({
  dob,
  policyNumber,
  applicationNumber,
  clientSearchRequirementsURL,
  getRequirementsDetailsURL,
  markReceivedURL,
  generateDMSUploadURL,
}) => {
  const [additionalDocument, setAdditionalDocument] = useState();
  const [uploadFiles, setUploadFiles] = useState({});
  const [loader, setLoader] = useState(false);

  useEffect(() => {
    getClientRequest();
  }, []);

  const getClientRequest = () => {
    const element = document.getElementById("additional-doc-li");

    callPost(clientSearchRequirementsURL, {
      dob: dob,
      policyNo: policyNumber,
    })
      .then(({ status = false, responseData, errors = [] }) => {
        if (status) {
          let additionalDocsArray = [];

          const promises = [];

          responseData?.nonMedicalRequirements?.forEach(function (resp, i) {
            if ("ORD" == resp.reqStatus && !resp.reqRecievedDate) {
              const promise = callGet(
                `${getRequirementsDetailsURL}?reqCode=${resp.reqCode}`
              )
                .then((res) => {
                  res.responseData.docList = resp?.docList || [];
                  !res.responseData.showAllow ||
                    (CASE_1_STRING !=
                      res.responseData.showAllow.toUpperCase() &&
                      CASE_2_STRING !=
                        res.responseData.showAllow.toUpperCase() &&
                      CASE_3_STRING !=
                        res.responseData.showAllow.toUpperCase()) ||
                    additionalDocsArray.push(res.responseData);
                })
                .catch(() => {});
              promises.push(promise);
            }
          });
          Promise.all(promises).then(() => {
            if (additionalDocsArray.length <= 0) {
              element.remove();
            } else {
              setAdditionalDocument(additionalDocsArray);
            }
          });
        } else {
          element.remove();
        }
      })
      .catch(() => {
        showFailure(DEFAULT_ERROS.default);
        element.remove();
      });
  };

  const documentSubmission = () => {
    if (Object.entries(uploadFiles).length == 0) {
      showFailure(Liferay.Language.get("label-at-please-upload-document"));
      return;
    }

    setLoader(true);

    Object.entries(uploadFiles).forEach(([key, upload]) => {
      upload?.forEach((e) => {
        const xhr = new XMLHttpRequest();
        xhr.open("PUT", e.url);
        xhr.onload = () => {};
        xhr.setRequestHeader("Content-Type", e.file.type);
        xhr.send(e.file);
      });
    });
    const markArray = [];

    Object.entries(uploadFiles).forEach(([key, upload]) => {
      markArray.push({
        requirementStatus: "rcd",
        applicationNumber: policyNumber,
        comment: "",
        mainRequirementId: key,
        requirementClosedOn: new Date()
          .toISOString()
          .split(".")[0]
          .split("T")[0],
      });
    });

    if (!markArray || markArray.length <= 0) {
      showFailure(Liferay.Language.get("label-at-please-upload-document"));
    }

    callPost(markReceivedURL, markArray)
      .then(({ status = false, responseData, errors = [] }) => {
        if (!status) {
          showFailure(errors.length > 0 ? errors[0] : DEFAULT_ERROS.default);
        } else {
          showSuccess();
          setAdditionalDocument([]);
          const remaining = additionalDocument.filter(({ requirementId }) => {
            if (uploadFiles[requirementId]) {
              return false;
            }
            return true;
          });
          setAdditionalDocument(remaining);
          setUploadFiles({});
        }
        setLoader(false);
      })
      .catch(() => {
        showFailure(DEFAULT_ERROS.default);
        setLoader(false);
      });
  };

  return (
    <>
      {loader && <div class="preloader"></div>}

      <h2>{Liferay.Language.get("label-at-additional-documents-proof")}</h2>
      <p>{Liferay.Language.get("label-at-additional-documents-if-required")}</p>
      <div className="app-file-upload-main-wrapper">
        {additionalDocument?.length > 0 &&
          additionalDocument?.map((doc) => (
            <UploadDocument
              doc={doc}
              uploadFiles={uploadFiles}
              setUploadFiles={setUploadFiles}
              generateDMSUploadURL={generateDMSUploadURL}
              policyNumber={policyNumber}
            />
          ))}

        {additionalDocument && (
          <div class="center-btn">
            <button class="edto-btn-primary" onClick={documentSubmission}>
              {Liferay.Language.get("label-confirm-and-submit")}
            </button>
          </div>
        )}
        {!additionalDocument && (
          <p class="mt-5">
            {`${Liferay.Language.get(
              "label-at-submdocument-list-is-not-found-for-policy-number"
            )} ${policyNumber}`}
          </p>
        )}
      </div>
    </>
  );
};

export default AdditionalDocuments;

const UploadDocument = ({
  doc,
  uploadFiles,
  setUploadFiles,
  generateDMSUploadURL,
  policyNumber,
}) => {
  const [files, setFiles] = useState([]);
  const [loader, setLoader] = useState(false);
  const [validator, setValidator] = useState({
    msg: DEFAULT_ERROS.required,
    hasError: true,
    dropDownError: false,
  });

  const fileUpload = (event) => {
    const file = event.target?.files[0];

    let requirement = event.target?.id;

    if (file.size > 5000000) {
      setValidator({
        ...validator,
        msg: DEFAULT_ERROS.fileSize,
        hasError: true,
        dropDownError: false,
      });
      return;
    }

    if (!FILE_TYPES.includes(file.type)) {
      setValidator({
        ...validator,
        msg: DEFAULT_ERROS.fileTypes,
        hasError: true,
        dropDownError: false,
      });
      return;
    }

    if ($(`#select_${requirement}`).find(":selected").val() === "") {
      setValidator({
        ...validator,
        msg: DEFAULT_ERROS.documentType,
        hasError: false,
        dropDownError: true,
      });
      return;
    }

    setValidator({
      ...validator,
      msg: "",
      hasError: false,
      dropDownError: false,
    });

    setLoader(true);
    callPost(generateDMSUploadURL, {
      dmClass: "NewBusiness",
      documentType: $(`#select_${event.target?.id}`).find(":selected").val()
        ? $(`#select_${event.target?.id}`).find(":selected").val()
        : "RECEIPT",
      contentType: $(`#select_${event.target?.id}`)
        .find(":selected")
        .attr("data-contenttype")
        ? $(`#select_${event.target?.id}`)
            .find(":selected")
            .attr("data-contenttype")
        : "RECEIPTING DOCS",
      policyNumber: policyNumber,
      fileName: file.name,
    })
      .then(({ status = false, responseData, errors = [] }) => {
        if (status) {
          const resdata = JSON.parse(responseData);
          resdata.file = file;
          resdata.requirementId = event.target?.id;

          setFiles([...files, { name: file.name, id: resdata.id }]);

          setUploadFiles({
            ...uploadFiles,
            [requirement]: uploadFiles[requirement]
              ? [...uploadFiles[requirement], resdata]
              : [resdata],
          });
        }
        setLoader(false);
      })
      .catch(() => {
        setLoader(false);
      });
  };

  const removeFiles = (id, requirementId) => {
    setLoader(true);
    let activeFiles = files.filter((file) => id != file.id);

    setFiles(activeFiles);
    if (activeFiles?.length <= 0) {
      setValidator({
        ...validator,
        msg: DEFAULT_ERROS.required,
        hasError: true,
        dropDownError: false,
      });
    }
    if (uploadFiles[requirementId]?.length > 0) {
      const activeDocs = uploadFiles[requirementId].filter(
        (file) => id != file.id
      );

      if (activeDocs?.length > 0) {
        uploadFiles[requirementId] = activeDocs;
      } else {
        delete uploadFiles[requirementId];
      }

      setUploadFiles({ ...uploadFiles });
    }
    setLoader(false);
  };

  useEffect(() => {
    $(".ad-select2-dropClass").select2({
      placeholder: Liferay.Language.get("label-at-document-type"),
      minimumResultsForSearch: Infinity,
    });
  }, []);

  return (
    <>
      {loader && <div class="preloader"></div>}
      <div class="file-upload-inner-wrapper">
        <div class="file-title">
          <h4>{doc.mainRequirement}</h4>
        </div>
        <div class="file-details">
          {doc?.docList?.length > 0 && (
            <>
              <div class="select-container">
                <select
                  class="select2-hidden-accessible select2-dropClass ad-select2-dropClass"
                  id={`select_${doc.requirementId}`}
                >
                  <option></option>
                  {doc?.docList?.map((item) => (
                    <option
                      value={item.documenttype}
                      data-contenttype={item.contenttype}
                    >
                      {item.documenttype}
                    </option>
                  ))}
                </select>
              </div>
              {validator.dropDownError && (
                <div className="error text-left">{validator.msg}</div>
              )}{" "}
            </>
          )}
          <span>
            <svg
              width="41"
              height="41"
              viewBox="0 0 41 41"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M20.7523 18.378C20.7223 18.3397 20.684 18.3087 20.6403 18.2874C20.5966 18.2661 20.5487 18.2551 20.5 18.2551C20.4514 18.2551 20.4034 18.2661 20.3597 18.2874C20.316 18.3087 20.2778 18.3397 20.2478 18.378L15.7634 24.0515C15.7265 24.0987 15.7035 24.1554 15.6972 24.215C15.6909 24.2746 15.7016 24.3348 15.7279 24.3887C15.7542 24.4426 15.7951 24.488 15.846 24.5197C15.8969 24.5514 15.9557 24.5681 16.0157 24.568H18.9746V34.2735C18.9746 34.4497 19.1187 34.5938 19.2949 34.5938H21.6972C21.8734 34.5938 22.0175 34.4497 22.0175 34.2735V24.572H24.9844C25.2527 24.572 25.4008 24.2637 25.2367 24.0555L20.7523 18.378Z"
                fill="#DDDDDD"
              ></path>
              <path
                d="M32.4877 14.6823C30.6539 9.84561 25.9813 6.40625 20.508 6.40625C15.0347 6.40625 10.3621 9.8416 8.52832 14.6783C5.09697 15.5792 2.5625 18.7062 2.5625 22.4219C2.5625 26.8462 6.146 30.4297 10.5663 30.4297H12.1719C12.348 30.4297 12.4922 30.2855 12.4922 30.1094V27.707C12.4922 27.5309 12.348 27.3867 12.1719 27.3867H10.5663C9.21699 27.3867 7.94775 26.8502 7.00283 25.8772C6.06191 24.9083 5.56143 23.603 5.60547 22.2497C5.6415 21.1927 6.00186 20.1997 6.65449 19.3629C7.32314 18.5101 8.26006 17.8895 9.30107 17.6132L10.8186 17.2168L11.3751 15.7514C11.7194 14.8385 12.1999 13.9856 12.8045 13.2129C13.4014 12.447 14.1084 11.7737 14.9025 11.2149C16.5481 10.0578 18.486 9.44521 20.508 9.44521C22.53 9.44521 24.4679 10.0578 26.1135 11.2149C26.9103 11.7755 27.6149 12.4481 28.2115 13.2129C28.8161 13.9856 29.2966 14.8425 29.6409 15.7514L30.1935 17.2128L31.7069 17.6132C33.8771 18.1978 35.3945 20.1717 35.3945 22.4219C35.3945 23.7472 34.878 24.9964 33.9411 25.9333C33.4816 26.3955 32.9351 26.7619 32.333 27.0114C31.7309 27.2609 31.0854 27.3884 30.4337 27.3867H28.8281C28.652 27.3867 28.5078 27.5309 28.5078 27.707V30.1094C28.5078 30.2855 28.652 30.4297 28.8281 30.4297H30.4337C34.854 30.4297 38.4375 26.8462 38.4375 22.4219C38.4375 18.7103 35.911 15.5872 32.4877 14.6823Z"
                fill="#DDDDDD"
              ></path>
            </svg>
          </span>
          <div class="drag-drop-wrapper">
            <div class="file">
              <div class="file__input" id={`file__input_${doc.requirementId}`}>
                <input
                  class="file__input--file"
                  id={`${doc.requirementId}`}
                  type="file"
                  name="files[]"
                  onChange={fileUpload}
                  accept="image/*,.pdf"
                />
                <label
                  class="file__input--label"
                  for={`${doc.requirementId}`}
                  data-text-btn="Upload"
                >
                  {Liferay.Language.get("label-drag-and-drop-or")}
                  <span>
                    {Liferay.Language.get("label-browse-your-computer")}
                  </span>
                </label>
              </div>
              {files?.map((file) => (
                <div
                  className="file__value"
                  onClick={() => removeFiles(file.id, doc.requirementId)}
                >
                  <div className="file__value--text">{file.name}</div>
                  <div
                    className="file__value--remove"
                    data-id={file.id}
                    onClick={() => removeFiles(file.id, doc.requirementId)}
                  >
                    x
                  </div>
                </div>
              ))}
              <p>{Liferay.Language.get("label-at-size-limit-5-mb")}</p>
              {validator.hasError && (
                <div className="error">{validator.msg}</div>
              )}
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
