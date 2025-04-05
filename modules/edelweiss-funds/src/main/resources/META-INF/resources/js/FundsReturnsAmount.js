import React, { useState, useEffect } from "react";

const FundsReturnsAmount = ({
  index,
  returnAmount,
  totalAllocation,
  setTotalAllocation,
  setAllocationGraph,
  allocationGraph,
  shortName,
}) => {
  const [allocation, setAllocation] = useState(
    index == 0 ? totalAllocation : 0
  );
  const [amount, setAmount] = useState("0");

  const increaseAllocation = () => {
    if (allocation < 100 && totalAllocation < 100) {
      setTotalAllocation((prev) => prev + 10);
      setAllocation((prev) => prev + 10);
    }
  };

  const decreaseAllocation = () => {
    if (allocation != 0 && totalAllocation <= 100) {
      setTotalAllocation((prev) => prev - 10);
      setAllocation((prev) => prev - 10);
    }
  };

  useEffect(() => {
    setAllocationGraph((prev) => {
      return { ...prev, [shortName]: `${allocation}%` };
    });
  }, [allocation]);

  useEffect(() => {
    /*if (totalAllocation == 100) {
      let finalAmount = parseInt(returnAmount.replace(/,/g, ""));
      finalAmount = Math.round((finalAmount / 100) * allocation);
      setAmount(finalAmount.toLocaleString("en-IN"));
    }*/
    let finalAmount = parseInt(returnAmount.replace(/,/g, ""));
    finalAmount = Math.round((finalAmount / 100) * allocation);
    setAmount(finalAmount.toLocaleString("en-IN"));
  }, [returnAmount, totalAllocation]);

  return (
    <React.Fragment>
      <td>
        <div class="allocation">
            <a
              href="javascript:void(0);"
              class="decrease"
              onClick={decreaseAllocation}
            >
              -
            </a>
          <input
            type="text"
            class="form-control allocation-input"
            placeholder=""
            value={`${allocation} %`}
          />
            <a
              href="javascript:void(0);"
              class="increase"
              onClick={increaseAllocation}
            >
              +
            </a>
        </div>
      </td>
      <td>₹ {amount}</td>
    </React.Fragment>
  );
};

export default FundsReturnsAmount;
