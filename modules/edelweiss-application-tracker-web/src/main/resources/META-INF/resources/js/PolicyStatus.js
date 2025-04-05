import React, { useEffect, useState } from "react";
import { callGet, callPost } from "./utilities/utility";

const PolicyStatus = ({
  clientAppTrackerURL,
  getDMSListURL,
  generateDownloadURL,
  dob,
  policyNo,
}) => {
  const [policyStatus, setPolicyStatus] = useState({});
  const [policyBond, setPolicyBond] = useState();

  useEffect(() => {
    callPost(clientAppTrackerURL, {
      dob: dob,
      policyNo: policyNo,
    }).then(({ status = false, responseData, errors = [] }) => {
      const ps = JSON.parse(responseData ?? "{}");
      if (ps?.applicationExternalStatus === "Issued") {
        $(".ps-status-icon").addClass("complete");
        $(".ps-status-icon").html(
          '<img src="/o/edelweisstokio-theme/images/check-tick.png"></img>'
        );
        $(".ps-status-text").text(ps?.applicationExternalStatus);
        $(".ps-status-text").removeClass("text-grey");
        $(".ps-status-text").addClass("text-green");
      } else if (
        ps?.applicationExternalStatus === "Rejected" ||
        ps?.applicationExternalStatus === "Terminated"
      ) {
        $(".ps-status-text").text(ps?.applicationExternalStatus);
        $(".ps-status-text").removeClass("text-grey");
        $(".ps-status-text").addClass("text-red");
      } else if (ps?.applicationExternalStatus === "Normal in Progress") {
        $(".ps-status-text").text(Liferay.Language.get("label-pending"));
        $(".ps-status-text").removeClass("text-grey");
        $(".ps-status-text").addClass("text-red");
      } else if (ps?.message === "No data Found") {
        $(".ps-status-text").text(
          Liferay.Language.get("label-at-under-review")
        );
      }
      setPolicyStatus(ps);
    });

    callGet(`${getDMSListURL}?policyNumber=${policyNo}`).then(
      ({ status = false, responseData = {}, errors = [] }) => {
        const documentsList = JSON.parse(responseData ?? "{}");
        let downloadId;
        documentsList.documents.forEach(function (document) {
          "POLICY BOND - WITH LOGO" == document.documentType &&
            (downloadId = document.id);
        });

        if (downloadId) {
          callGet(`${generateDownloadURL}?id=${downloadId}`).then(
            ({ status = false, responseData = {}, errors = [] }) => {
              const download = JSON.parse(responseData ?? "{}");
              setPolicyBond(download.url);
            }
          );
        }
      }
    );
  }, []);

  return (
    <>
      <h2>{Liferay.Language.get("label-at-policy-status")}</h2>
      <p> {Liferay.Language.get("label-at-policy-status-description")}</p>

      <GetPolicyStatus policyStatus={policyStatus} policyBond={policyBond} />
    </>
  );
};

export default PolicyStatus;

const GetPolicyStatus = ({ policyStatus, policyBond }) => {
  if (policyStatus?.applicationExternalStatus === "Issued") {
    return (
      <div class="issue-wrapper">
        <p>
          <span>
            <svg
              width="18"
              height="18"
              viewBox="0 0 18 18"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <g clip-path="url(#clip0_1853_2623)">
                <path
                  fill-rule="evenodd"
                  clip-rule="evenodd"
                  d="M0 9C0 6.61305 0.948212 4.32387 2.63604 2.63604C4.32387 0.948212 6.61305 0 9 0C11.3869 0 13.6761 0.948212 15.364 2.63604C17.0518 4.32387 18 6.61305 18 9C18 11.3869 17.0518 13.6761 15.364 15.364C13.6761 17.0518 11.3869 18 9 18C6.61305 18 4.32387 17.0518 2.63604 15.364C0.948212 13.6761 0 11.3869 0 9H0ZM8.4864 12.852L13.668 6.3744L12.732 5.6256L8.3136 11.1468L5.184 8.5392L4.416 9.4608L8.4864 12.8532V12.852Z"
                  fill="#00C511"
                ></path>
              </g>
              <defs>
                <clipPath id="clip0_1853_2623">
                  <rect width="18" height="18" fill="white"></rect>
                </clipPath>
              </defs>
            </svg>
            &nbsp;
            {Liferay.Language.get("label-at-issued")}
          </span>
          {Liferay.Language.get("label-at-preparing-documents-for-dispatch")}
        </p>
        <p>{policyStatus?.policyIssuanceDate.split("T")[0] ?? ""}</p>
        <p>
        {policyBond && (
          <a href={policyBond} download>
            <span>
              <svg
                width="12"
                height="15"
                viewBox="0 0 12 15"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  d="M11 14.1818H1C0.734784 14.1818 0.48043 14.0741 0.292893 13.8823C0.105357 13.6905 0 13.4303 0 13.1591V7.02273C0 6.75148 0.105357 6.49135 0.292893 6.29955C0.48043 6.10775 0.734784 6 1 6H2.5V7.02273H1V13.1591H11V7.02273H9.5V6H11C11.2652 6 11.5196 6.10775 11.7071 6.29955C11.8946 6.49135 12 6.75148 12 7.02273V13.1591C12 13.4303 11.8946 13.6905 11.7071 13.8823C11.5196 14.0741 11.2652 14.1818 11 14.1818Z"
                  fill="#0B7EFA"
                ></path>
                <path
                  d="M8.56746 3.33779L6.77246 5.15104V0H5.77246V5.15104L3.97746 3.33779L3.27246 4.05195L6.27246 7.09091L9.27246 4.05195L8.56746 3.33779Z"
                  fill="#0B7EFA"
                ></path>
              </svg>
            </span>
            {Liferay.Language.get("label-at-download-policy-bond-document")}
          </a>
        )}
        </p>
      </div>
    );
  } else if (
    policyStatus?.applicationExternalStatus === "Rejected" ||
    policyStatus?.applicationExternalStatus === "Terminated"
  ) {
    return (
      <div class="declined-wrapper">
        <div class="table-form-wrapper">
          <div class="td-form">
            <h3>
              <span>
                <svg
                  width="18"
                  height="18"
                  viewBox="0 0 18 18"
                  fill="none"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    d="M9.0001 0.599976C6.77228 0.599976 4.63571 1.48497 3.0604 3.06028C1.4851 4.63558 0.600098 6.77216 0.600098 8.99998C0.600098 11.2278 1.4851 13.3644 3.0604 14.9397C4.63571 16.515 6.77228 17.4 9.0001 17.4C11.2279 17.4 13.3645 16.515 14.9398 14.9397C16.5151 13.3644 17.4001 11.2278 17.4001 8.99998C17.4001 6.77216 16.5151 4.63558 14.9398 3.06028C13.3645 1.48497 11.2279 0.599976 9.0001 0.599976ZM13.7891 12.061L12.0601 13.79L9.0001 10.729L5.9391 13.789L4.2101 12.06L7.2721 8.99998L4.2111 5.93898L5.9401 4.21098L9.0001 7.27098L12.0611 4.20998L13.7901 5.93898L10.7281 8.99998L13.7891 12.061Z"
                    fill="#CC2C2C"
                  ></path>
                </svg>
              </span>
              {Liferay.Language.get("label-at-declined")}
            </h3>
          </div>
        </div>
      </div>
    );
  }
  return (
    <div class="under-review-wrapper">
      <img src="/o/edelweisstokio-theme/images/review.png" alt="icon" />
      <p>{Liferay.Language.get("label-at-under-review")}</p>
    </div>
  );
};
