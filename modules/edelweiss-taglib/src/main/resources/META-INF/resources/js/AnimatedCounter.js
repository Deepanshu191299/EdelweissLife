import { useIsMounted, usePrevious } from "@liferay/frontend-js-react-web";
import classNames from "classnames";
import PropTypes from "prop-types";
import React, { useEffect, useState } from "react";

function SlidingText({ current, previous }) {
  const [animated, setAnimated] = useState(false);
  const direction = current > previous ? "up" : "down";
  const isMounted = useIsMounted();
  const maxLength = current.toString().length + 1;

  const finishAnimation = () => {
    if (isMounted()) {
      setAnimated(false);
    }
  };

  useEffect(() => {
    if (
      Number.isInteger(current) &&
      Number.isInteger(previous) &&
      current !== previous
    ) {
      setAnimated(true);
    }
  }, [current, previous]);

  return (
    <span
      className={classNames("counter", {
        [`counter-animated-${direction}`]: animated,
      })}
      onAnimationEnd={finishAnimation}
      style={{
        minWidth: `${maxLength}ch`,
      }}
    >
      <span className="current">{current}</span>
    </span>
  );
}

function AnimatedCounter({ counter }) {
  const prevCounter = usePrevious(counter);

  return <SlidingText current={counter} previous={prevCounter} />;
}

AnimatedCounter.propTypes = {
  counter: PropTypes.number.isRequired,
};

export default AnimatedCounter;
