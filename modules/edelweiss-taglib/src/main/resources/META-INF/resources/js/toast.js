import { openToast } from "frontend-js-web";

export function errorToast(
  message = Liferay.Language.get("an-unexpected-error-occurred"),
  title = Liferay.Language.get("error")
) {
  openToast({
    message,
    title: `${title}:`,
    type: "danger",
  });
}
