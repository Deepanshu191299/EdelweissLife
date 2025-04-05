import React, { useState, useEffect } from "react";
import FundDetails from "./FundDetails";

function Funds({ funds }) {
  const [risk, setRisk] = useState("All");

  useEffect(() => {
    $(".funds-card-wrapper .owl-carousel").owlCarousel({
      loop: false,
      margin: 20,
      nav: true,
      navText: [
        '<img src="/o/edelweisstokio-theme/images/prev-arrow.svg">',
        '<img src="/o/edelweisstokio-theme/images/prev-arrow.svg">',
      ],
      dots: true,
      autoplay: false,
      smartSpeed: 1200,
      responsive: {
        0: {
          items: 1,
        },
        600: {
          items: 1,
        },
        911: {
          items: 3,
        },
        1300: {
          items: 3,
        },
      },
    });
  }, [risk]);

  return (
    <div className="funds-card-wrapper testi-slider">
      <div className="mb-4 mt-2 select-wrapper form-group select-pad">
        <select
          className="form-control"
          name="risk"
          onChange={(e) => {
            let owl = $(".owl-carousel");
            owl.trigger("destroy.owl.carousel");
            owl.addClass("off");
            setRisk(e.target.value);
          }}
        >
          <option value="All">All</option>
          <option value="High">High Risk</option>
          <option value="Balanced">Medium Risk</option>
          <option value="Low">Low Risk</option>
        </select>
      </div>
      <div className="owl-carousel owl-theme">
        {funds
          .filter((item) => risk === "All" || item.riskCategory === risk)
          .map((item) => {
            let benchMarkReturnSinceInception = 0.0;
            let fundReturnSinceInception = 0.0;

            let benchMarkReturns = item.benchMarkReturns
              .split(",")
              .map((item) => {
                let arr = item.split(":");

                if (arr[0].trim() === "Since Inception") {
                  benchMarkReturnSinceInception = parseFloat(
                    arr[1].trim()
                  ).toFixed(2);
                }

                return {
                  key: arr[0].trim(),
                  value: parseFloat(arr[1].trim()).toFixed(2),
                };
              });
            let fundReturns = item.fundReturns.split(",").map((item) => {
              let arr = item.split(":");

              if (arr[0].trim() === "Since Inception") {
                fundReturnSinceInception = parseFloat(arr[1].trim()).toFixed(2);
              }

              return {
                key: arr[0].trim(),
                value: parseFloat(arr[1].trim()).toFixed(2),
              };
            });

            return (
              <div className="item p-2">
                <div className="capital-box pb-4 w-auto">
                  <div className="top-tag">
                    <h4>{item.hashTag}</h4>
                  </div>
                  <h5 className="text-center fontbold pt-4">{item.name}</h5>
                  <div className="total-annum px-2">
                    <p className="fontregular fs14">{item.fundDescription}</p>
                  </div>
                  <FundDetails
                    item={item}
                    fundReturnSinceInception={fundReturnSinceInception}
                    benchMarkReturnSinceInception={
                      benchMarkReturnSinceInception
                    }
                    benchMarkReturns={benchMarkReturns}
                    fundReturns={fundReturns}
                  />
                </div>
              </div>
            );
          })}
      </div>
    </div>
  );
}

export default Funds;
