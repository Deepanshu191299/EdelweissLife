import React, { useCallback } from "react";
import RatingsLike from "./RatingsLike";
import { fetch, objectToFormData } from "frontend-js-web";
import { errorToast } from "./toast";

const PORTLET_NS = Liferay.Util.getPortletNamespace(
  "in_edelweiss_guest_rating_web_EdelweissGuestRatingWebPortlet"
);

const Ratings = ({
  classNameId,
  classPK,
  contentTitle = '',
  guestIp,
  guestRatingsURL,
  statsURL,
  entryURL,
  ...restProps
}) => {

  const sendVoteRequest = useCallback(
    (score) => {
      const body = objectToFormData({
        [`${PORTLET_NS}classNameId`]: classNameId,
        [`${PORTLET_NS}classPK`]: classPK,
        [`${PORTLET_NS}contentTitle`]: contentTitle,
        p_auth: Liferay.authToken,
        p_l_id: themeDisplay.getPlid(),
        [`${PORTLET_NS}score`]: score,
      });

      let URL = Liferay.Util.addParams(
        `p_p_resource_id=/guest_ratings_entry`,
        guestRatingsURL
      );

      return fetch(URL, {
        body,
        method: "POST",
      })
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            errorToast();
          }
        })
        .catch(() => {
          errorToast();
        });
    },
    [classNameId, classPK]
  );

  return <RatingsLike sendVoteRequest={sendVoteRequest} {...restProps} />;
};

export default Ratings;
