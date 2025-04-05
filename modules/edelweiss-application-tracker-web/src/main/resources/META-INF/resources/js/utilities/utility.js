export const SIDE_NAV_ITEMS = [
  {
    stepName: Liferay.Language.get("apptracker-application-submitted"),
    defaultStatus: "Completed",
    icon: "",
    active: true,
    defaultStep: true,
    dataTarget: "pills-home",
  },
  {
    stepName: Liferay.Language.get("apptracker-auto-debit-setup"),
    defaultStatus: "Completed",
    icon: "https://mythememarket.com/liferay/edelweisstokio/images/auto-debit.png",
    active: false,
    defaultStep: false,
    dataTarget: "pills-debit",
    apiKey: "enachStatus",
  },
  {
    stepName: Liferay.Language.get("apptracker-document-proof"),
    defaultStatus: "Completed",
    icon: "https://mythememarket.com/liferay/edelweisstokio/images/doc.png",
    active: false,
    defaultStep: false,
    dataTarget: "pills-document",
    apiKey: "documentUploadStatus",
  },
  {
    stepName: Liferay.Language.get("apptracker-video-verification"),
    defaultStatus: "Completed",
    icon: "https://mythememarket.com/liferay/edelweisstokio/images/doc.png",
    active: false,
    defaultStep: false,
    dataTarget: "pills-video",
    apiKey: "pivcStatus",
  },
  {
    stepName: Liferay.Language.get("apptracker-medical-scheduling"),
    defaultStatus: "Completed",
    icon: "https://mythememarket.com/liferay/edelweisstokio/images/doc.png",
    active: false,
    defaultStep: false,
    dataTarget: "pills-medical",
    apiKey: "tpaStatus",
  },
  {
    stepName: Liferay.Language.get("apptracker-additional-documents"),
    defaultStatus: "Under Review",
    icon: "https://mythememarket.com/liferay/edelweisstokio/images/doc.png",
    active: false,
    defaultStep: true,
    dataTarget: "pills-additionaldoc",
  },
  {
    stepName: Liferay.Language.get("apptracker-policy-status"),
    defaultStatus: "Completed",
    icon: "https://mythememarket.com/liferay/edelweisstokio/images/policy.png",
    active: false,
    defaultStep: true,
    dataTarget: "pills-policy",
  },
];
export const SIDE_NAV_STEP_COMPLETE_ICON =
  "https://mythememarket.com/liferay/edelweisstokio/images/check-tick.png";

export const getTabStatus = (index, status) => {
  if (index === 0 || status === "Completed") {
    return { tabStatus: status, tabFlag: true };
  }
  return { tabStatus: status, tabFlag: false };
};

export const callGet = (url, params) => {
  return Liferay.Util.fetch(buildQueryParamerter(url, params)).then(
    (response) => {
      return response.json();
    }
  );
};

export const callPost = (url, body) => {
  return Liferay.Util.fetch(url, {
    body: JSON.stringify(body),
    method: "POST",
    headers: new Headers({
      "Content-Type": "application/json",
    }),
  }).then((response) => {
    return response.json();
  });
};

function buildQueryParamerter(url, params) {
  let queryString = "?";
  params &&
    Object.keys(params).forEach((key, i) => {
      queryString = queryString + `${key}=${params[key]}&`;
    });
  return url + queryString.replace(/.$/, "");
}

export const showSuccess = (
  msg = Liferay.Language.get("label-at-documents-uploaded-successfuly")
) => {
  $("#ATModal").modal("show");
  $(".align-items-center").removeClass("hide");
  $(".vsucc-error-msg").text(msg);
};

export const showFailure = (
  msg = Liferay.Language.get(
    "label-at-service-is-temproraly-unavailable-please-try-after-some-time"
  )
) => {
  $("#ATModal").modal("show");
  $(".vsucc-error-msg").text(msg);
  $(".align-items-center").addClass("hide");
};
