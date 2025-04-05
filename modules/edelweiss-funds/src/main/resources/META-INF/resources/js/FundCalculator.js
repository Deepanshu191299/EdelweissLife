import React, { useState, useEffect } from "react";
import FundsReturnsAmount from "./FundsReturnsAmount";

const FundCalculator = ({ funds }) => {
  const [year, setYear] = useState("Since Inception");
  const [investAmount, setInvestAmount] = useState("1,00,000");
  const [investFor, setInvestFor] = useState("5");
  const [returnAmount, setReturnAmount] = useState("0");
  const [projectedReturns, setProjectedReturns] = useState("8");
  const [totalAllocation, setTotalAllocation] = useState(100);
  const [allocationGraph, setAllocationGraph] = useState({});

  const onChange = (k) => {
    setYear(k);
  };

  const changeInvestFor = (k) => {
    setInvestFor(k);
  };

  const onBlurChangeAmount = () => {
    let value = investAmount.replaceAll(",", "");
    if (
      (typeof value !== "undefined" || value !== null || value !== "") &&
      Number(value) > 100000
    ) {
      setInvestAmount(Number(value).toLocaleString("en-IN"));
    } else {
      setInvestAmount("1,00,000");
    }
  };

  const changeProjectedReturns = (k) => {
    setProjectedReturns(k);
  };

  const calculateReturns = () => {
    let pReturns = parseInt(projectedReturns);
    let investmentYear = parseInt(investFor);
    let investmentAmount = parseInt(investAmount.replace(/,/g, ""));
    var finalVal =
      investmentAmount * Math.pow(1 + pReturns / 100, investmentYear) +
      investmentAmount *
        ((Math.pow(1 + pReturns / 100, investmentYear) - 1) / (pReturns / 100));

    finalVal = Math.round(finalVal);
    setReturnAmount(finalVal.toLocaleString("en-IN"));
  };

  useEffect(() => {
    let value = investAmount.replaceAll(",", "");

    if (Number(value) >= 100000 && totalAllocation == 100) {
      calculateReturns();
    }
  }, [investAmount, investFor, projectedReturns, totalAllocation]);

  return (
    <>
      <div className="container pb-4">
        <div className="d-flex section-txt line-of-inputs">
          <span>{Liferay.Language.get("funds-if-you-invest")}</span>
          <label className="custom-field two w-auto p-0 py-md-2">
            <input
              name="investAmount"
              className="form-control"
              value={investAmount}
              onChange={(e) => {
                const result = e.target.value.replace(/[^0-9]/gi, "");
                setInvestAmount(Number(result).toLocaleString("en-IN"));
              }}
              onBlur={onBlurChangeAmount}
            ></input>
            <span class="placeholder"></span>
          </label>
          <span>{Liferay.Language.get("funds-investing-for")}</span>
          <div className="form-group mb-0 select-pad">
	          <select
	            name="investFor"
	            className="form-control w-auto"
	            value={investFor}
	            onChange={(e) => changeInvestFor(e.target.value)}
	          >
	            <option value="5">5 years</option>
	            <option value="10">10 years</option>
	            <option value="15">15 years</option>
	            <option value="20">20 years</option>
	            <option value="25">25 years</option>
	            <option value="30">30 years</option>
	          </select>
          </div>
          <span>
            {Liferay.Language.get(
              "funds-annually-your-investment-value-will-be"
            )}
          </span>
          <p className="fontbold mt-2 mt-md-0">&#8377; {returnAmount}</p>
        </div>
        <div className="section-txt">
          <span>
            {Liferay.Language.get(
              "funds-past-return-values-benchmark-return-values-are-updated-as-of-28-th-february-2023"
            )}
          </span>
        </div>
      </div>

      <div class="customize-fund-wrapper active">
        <div class="table-allocation-wrapper">
          <div class="table-responsive">
            <table class="table fixed-table">
              <thead>
                <tr>
                  <th>{Liferay.Language.get("funds-fund-name")}</th>
                  <th>{Liferay.Language.get("funds-fund-inception-date")}</th>
                  <th>{Liferay.Language.get("funds-allocation")}</th>
                  <th>{Liferay.Language.get("funds-projected-returns")}</th>
                  <th class="table-dark-bg">
                    {Liferay.Language.get("funds-past-returns")}
                  </th>
                  <th class="table-dark-bg">
                    {Liferay.Language.get(
                      "funds-benchmark-returns-percentage-sumbol"
                    )}
                  </th>
                </tr>
                <tr>
                  <th></th>
                  <th></th>
                  <th></th>
                  <th>
                    <div class="labelInputGroup">
                      <div class="select-container">
                        <select
                          class="form-control"
                          value={projectedReturns}
                          onChange={(e) =>
                            changeProjectedReturns(e.target.value)
                          }
                        >
                          <option value="4">@4%</option>
                          <option value="8">@8%</option>
                        </select>
                      </div>
                    </div>
                  </th>
                  <th colspan="2" class="table-dark-bg">
                    <div class="labelInputGroup">
                      <div class="select-container">
                        <select
                          class="form-control"
                          onChange={(e) => onChange(e.target.value)}
                        >
                          <option value={"Since Inception"}>
                            Since Inception
                          </option>
                          {funds &&
                            funds[0].benchMarkReturns
                              .split(",")
                              .filter(
                                (item) => !item.includes("Since Inception")
                              )
                              .map((item) => {
                                let arr = item.split(":");
                                return {
                                  key: arr[0].trim(),
                                  value: parseFloat(arr[1].trim()).toFixed(2),
                                };
                              })
                              .sort(function (a, b) {
                                return Number(a.key) - Number(b.key);
                              })
                              .map(({ key }) => {
                                return (
                                  <option value={key} key={key}>
                                    {Number(key) > 1
                                      ? `${key} years`
                                      : `${key} year`}
                                  </option>
                                );
                              })}
                        </select>
                      </div>
                    </div>
                  </th>
                </tr>
              </thead>
              <tbody className="equity-section-tbody">
                {funds.map((item, index) => {
                  let benchMarkReturnSinceInception = 0.0;
                  let fundReturnSinceInception = 0.0;

                  let benchMarkReturns = item.benchMarkReturns
                    .split(",")
                    .find((item) => {
                      let arr = item.split(":");
                      return arr[0].trim() === year;
                    });

                  benchMarkReturnSinceInception = parseFloat(
                    benchMarkReturns.split(":")[1].trim()
                  ).toFixed(2);

                  let fundReturns = item.fundReturns.split(",").find((item) => {
                    let arr = item.split(":");
                    return arr[0].trim() === year;
                  });

                  fundReturnSinceInception = parseFloat(
                    fundReturns.split(":")[1].trim()
                  ).toFixed(2);

                  return (
                    <tr>
                      <td className="fund-name">
                        <span class="first-line">{item.name}</span>
                        <span class="second-line">
                          Benchmark: {item.benchMark}
                        </span>
                        <span class="second-line">SFIN: {item.sfin}</span>
                        <span
                          class={`tbl-line cs-color-${item.shortName}`}
                        ></span>
                      </td>
                      <td>{item.inceptionDate}</td>
                      <FundsReturnsAmount
                        returnAmount={returnAmount}
                        index={index}
                        shortName={item.shortName}
                        totalAllocation={totalAllocation}
                        setAllocationGraph={setAllocationGraph}
                        setTotalAllocation={setTotalAllocation}
                        allocationGraph={allocationGraph}
                      />
                      <td>{fundReturnSinceInception}</td>
                      <td>{benchMarkReturnSinceInception}</td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
        <div class="progress-allocation-wrapper">
          <div class="allocatifund-allocation-mobon-tooltip allocation-left">
            <span>{totalAllocation}%</span>
          </div>
          <div class="allocation-bar">
            {funds.map((item, index) => {
              return (
                <span
                  class={`color-${item.shortName}`}
                  style={{
                    height: allocationGraph[item.shortName],
                  }}
                ></span>
              );
            })}
          </div>
        </div>
      </div>
    </>
  );
};

export default FundCalculator;
