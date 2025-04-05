import ClayButton from "@clayui/button";
import ClayIcon from "@clayui/icon";
import { useIsMounted } from "@liferay/frontend-js-react-web";

import PropTypes from "prop-types";
import React, { useState } from "react";

import { useDebounceCallback } from "./RatingUtil";

const SCORE_LIKE = 1;
const SCORE_INITIAL_LIKE = 0;

const RatingsLike = ({
  disabled = false,
  initialLiked = false,
  initialTitle,
  positiveVotes = 0,
  sendVoteRequest,
  interval = 500,
}) => {
  const [isDisabled, setIsDisabled] = useState(disabled);
  const [liked, setLiked] = useState(initialLiked);
  const [totalLikes, setTotalLikes] = useState(positiveVotes);

  const isMounted = useIsMounted();

  const [handleSendVoteRequest] = useDebounceCallback((score) => {
    sendVoteRequest(score)
      .then(({ totalScore } = {}) => {
        if (isMounted() && totalScore) {
          setTotalLikes(Math.round(totalScore));
        }
        setIsDisabled(false);
      })
      .catch((e) => {
        setIsDisabled(false);
      });
  }, interval);

  const toggleLiked = () => {
    if (!liked) {
      setIsDisabled(true);

      const score = Number(totalLikes) > 0 ? SCORE_LIKE : SCORE_INITIAL_LIKE;

      setLiked(!liked);
      setTotalLikes(totalLikes + score);
      handleSendVoteRequest(score);
    }
  };


  return (
    <div className="ratings-like">
      <ClayButton
        disabled={isDisabled}
        borderless
        displayType="secondary"
        onClick={toggleLiked}
        small
        value={totalLikes}
      >
        <span className="c-inner" tabIndex="-1">
          <span className="inline-item inline-item-before">
            <span className="off">
              <ClayIcon
                symbol="thumbs-up"
                spritemap={Liferay.Icons.spritemap}
              />
            </span>
          </span>

          <span className="inline-item likes">
            <span
              className="counter"
              style={{
                minWidth: `2ch`,
              }}
            >
              <span className="current">{totalLikes}</span>
            </span>
          </span>
        </span>
      </ClayButton>
    </div>
  );
};

RatingsLike.propTypes = {
  disabled: PropTypes.bool,
  initialLiked: PropTypes.bool,
  initialTitle: PropTypes.string,
  positiveVotes: PropTypes.number,
  sendVoteRequest: PropTypes.func.isRequired,
};

export default RatingsLike;
