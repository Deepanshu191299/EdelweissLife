import React, { useState } from "react";

function calculateYearDiff(inputDate) {
  // Ensure inputDate is a Date object
  if (!(inputDate instanceof Date)) {
    // Split the inputDate string into day, month, and year components
    const parts = inputDate.split('/');
    // Construct a new Date object using the components in the format yyyy-mm-dd
    inputDate = new Date(`${parts[2]}-${parts[1]}-${parts[0]}`);
    // If parsing fails or inputDate is still not a Date object, return 0
    if (!(inputDate instanceof Date) || isNaN(inputDate.getTime())) {
      return 0;
    }
  }

  const currentDate = new Date();
  const inputYear = inputDate.getFullYear();
  const currentYear = currentDate.getFullYear();

  return currentYear - inputYear;
}

function CargRetOption({ inceptionDate }) {
	console.log("CargRetOption loaded");
	  console.log("CargRetOption inceptionDate " + inceptionDate);
	  let dateOfYear = calculateYearDiff(inceptionDate);

	  // Ensure dateOfYear is capped at 7
	  dateOfYear = Math.min(dateOfYear, 7);

	  const options = [];
	  for (let i = 1; i <= dateOfYear; i++) {
		if (i === 6) continue;
	    options.push(
	      <option value={i} key={i}>
	        {i > 1 ? `${i} years` : `${i} year`}
	      </option>
	    );
	  }

	  return options;
}

function FundDetails({
  item,
  fundReturnSinceInception,
  benchMarkReturnSinceInception,
  benchMarkReturns,
  fundReturns,
}) {
  const [fundReturnValue, setFundReturnValue] = useState(
    fundReturnSinceInception
  );
  const [benchMarkReturnValue, setBenchMarkReturnValue] = useState(
    benchMarkReturnSinceInception
  );
  
  const onChange = (k) => {
    setBenchMarkReturnValue(
      benchMarkReturns.find(({ key }) => key === k).value
    );
    setFundReturnValue(fundReturns.find(({ key }) => key === k).value);
  };

  return (
    <>
      <div className="capital-text">
        <div className="equity-card-left">
          <p className="fontregular fs14">
            {Liferay.Language.get("funds-inception-date")}
          </p>
          <p className="fontbold fs14">{item.inceptionDate}</p>
        </div>
        <div className="equity-card-right">
          <p className="fontregular fs14">
            {Liferay.Language.get("funds-cagr-return")}
          </p>
          <select name="slct" className="bg-white fs14" onChange={(e) => onChange(e.target.value)}>
            <option value="Since Inception">Since Inception</option>
            <CargRetOption inceptionDate={item.inceptionDate}/>
          </select>
        </div>
      </div>

      <div className="capital-text">
        <div className="equity-card-left">
          <p className="fontregular fs14">
            {Liferay.Language.get("funds-fund-manager")}
          </p>
          <p className="fontbold fs14">{item.fundManager}</p>
        </div>
        <div className="equity-card-right">
          <p className="fontregular fs14">
            {Liferay.Language.get("funds-benchmark")}
          </p>
          <p className="fontbold fs14">{item.benchMark}</p>
        </div>
      </div>

      <div className="capital-text">
        <div className="equity-card-left">
          <p className="fontregular fs14">
            {Liferay.Language.get("funds-returns")}
          </p>
          <p className="fontbold fs14">{`${fundReturnValue}%`}</p>
        </div>
        <div className="equity-card-right">
          <p className="fontregular fs14">
            {Liferay.Language.get("funds-benchmark-returns")}
          </p>
          <p className="fontbold fs14">{`${benchMarkReturnValue}%`}</p>
        </div>
      </div>

      <div className="capital-text">
        <div className="equity-card-left">
          <p className="fontregular fs14">
            {Liferay.Language.get("funds-risk")}
          </p>
          <p className="fontbold fs14">{item.riskCategory}</p>
        </div>
        <div className="equity-card-right">
          <p className="fontregular fs14">
            {Liferay.Language.get("funds-current-nav")}
          </p>
          <p className="fontbold fs14">{item.currentNAV}</p>
        </div>
      </div>
    </>
  );
}

export default FundDetails;
