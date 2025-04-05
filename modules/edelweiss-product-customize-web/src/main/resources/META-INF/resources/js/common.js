function parseJSONString(str) {
	console.log("after parseJSON:::");
    return JSON.parse(str.replaceAll('\n', '\\n'));
}