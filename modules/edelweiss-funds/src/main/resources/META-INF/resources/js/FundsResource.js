import React, { useState, useEffect } from "react";
import Funds from "./Funds";
import FundCalculator from "./FundCalculator";
const FundsResource = (props) => {
  const [funds, setFunds] = useState();

  useEffect(() => {
    Liferay.Util.fetch(
      Liferay.Util.PortletURL.createResourceURL(themeDisplay.getLayoutURL(), {
        p_p_id: Liferay.Util.getPortletId(props.portletNamespace),
      }),
      {
        method: "GET",
      }
    )
      .then((response) => {
        if (response.ok) {
          return response.json();
        }
      })
      .then((response) => {
        if (response) {
          setFunds(response);
        }
      })
      .catch((e) => {
        console.error("Funds error", e);
      });
  }, []);

  if (funds && props.type == 0) {
    return <FundCalculator funds={funds} />;
  } else if (funds && props.type == 1) {
    return <Funds funds={funds} />;
  }

  return <div>Loading.........</div>;
};

export default FundsResource;
