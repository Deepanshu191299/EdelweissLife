package in.edelweiss.taglib.servlet.taglib;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import in.edelweiss.taglib.servlet.taglib.internal.servlet.ServletContextUtil;

/**
 * @author Ashish V
 *
 */
public class DynamicDropdownTag extends IncludeTag {

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getLabel() {
		return _label;
	}

	public void setLabel(String label) {
		this._label = label;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		this._value = value;
	}

	public String getCssClass() {
		return _cssClass;
	}

	public void setCssClass(String cssClass) {
		this._cssClass = cssClass;
	}

	public String getSourceUrl() {
		return _sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this._sourceUrl = sourceUrl;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		this._userName = userName;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		this._password = password;
	}

	public String getLabelParam() {
		return _labelParam;
	}

	public void setLabelParam(String labelParam) {
		this._labelParam = labelParam;
	}

	public String getValueParam() {
		return _valueParam;
	}

	public void setValueParam(String valueParam) {
		this._valueParam = valueParam;
	}

	public String getEntryListParam() {
		return _entryListParam;
	}

	public void setEntryListParam(String entryListParam) {
		this._entryListParam = entryListParam;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_name = StringPool.BLANK;
		_label = StringPool.BLANK;
		_value = StringPool.BLANK;
		_cssClass = StringPool.BLANK;
		_sourceUrl = StringPool.BLANK;
		_userName = StringPool.BLANK;
		_password = StringPool.BLANK;
		_entryListParam = StringPool.BLANK;
		_valueParam = StringPool.BLANK;
		_labelParam = StringPool.BLANK;

	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);
		setServletContext(ServletContextUtil.getServletContext());
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {

//		_log.debug("dyncmic dropdown attributes:");
//		_log.debug(_name);
//		_log.debug(_value);
//		_log.debug(_sourceUrl);
//		_log.debug(_userName);
//		_log.debug(_password);
//		_log.debug(_entryListParam);
//		_log.debug(_valueParam);
//		_log.debug(_labelParam);
//		_log.debug(_cssClass);
//
//		if (Validator.isNull(_userName) || Validator.isNull(_password)) {
//			_userName = PropsUtil.get(ConstantKeys.LIFERAY_USERNAME);
//			_password = PropsUtil.get(ConstantKeys.LIFERAY_PASSWORD);
//		}
//
//		_log.debug(_userName);
//		_log.debug(_password);
//
//		httpServletRequest.setAttribute("edelweiss-ui:dynamic-dropdown:cssClass", _cssClass);
//		httpServletRequest.setAttribute("edelweiss-ui:dynamic-dropdown:value", _value);
//		httpServletRequest.setAttribute("edelweiss-ui:dynamic-dropdown:name", _name);
//		httpServletRequest.setAttribute("edelweiss-ui:dynamic-dropdown:label", _label);
//
//		if (Validator.isNull(_entryListParam)) {
//			_entryListParam = "items";
//		}
//
//		ETIPCoreImpl edelweissTokioCommonUtil = new ETIPCoreImpl();
//		Map<String, String> optionsList = new HashMap<>();
//		JSONObject dataJSON = null;
//		try {
//			dataJSON = edelweissTokioCommonUtil.callGetAPI(new HashMap<>(), _sourceUrl, false, _userName, _password);
//			if (Validator.isNull(dataJSON) || Validator.isNull(dataJSON.getJSONArray(_entryListParam))) {
//				_log.warn("Unable to fetch the data from the End point: " + _sourceUrl + " with UserName: " + _userName
//						+ " & Password: " + _password);
//				httpServletRequest.setAttribute("edelweiss-ui:dynamic-dropdown:options-list", optionsList);
//				return;
//
//			}
//		} catch (ETIPSystemException e) {
//			_log.error("Unable to fetch the data from the End point: " + _sourceUrl + " with UserName: " + _userName
//					+ " & Password: " + _password);
//			if (_log.isDebugEnabled()) {
//				e.printStackTrace();
//			}
//
//		}
//
//		JSONArray dataJSONArray = dataJSON.getJSONArray(_entryListParam);
//		for (int i = 0; i < dataJSONArray.length(); i++) {
//			JSONObject listObject = dataJSONArray.getJSONObject(i);
//			if (Validator.isNotNull(listObject.getString(_labelParam))
//					&& Validator.isNotNull(listObject.getString(_valueParam))) {
//				optionsList.put(listObject.getString(_valueParam), listObject.getString(_labelParam));
//			}
//		}
//		httpServletRequest.setAttribute("edelweiss-ui:dynamic-dropdown:options-list", optionsList);

	}

	private static final String _PAGE = "/dynamic-dropdown/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(DynamicDropdownTag.class);

	private String _name;
	private String _label;
	private String _value;
	private String _cssClass;
	private String _sourceUrl;
	private String _userName;
	private String _password;
	private String _entryListParam;
	private String _labelParam;
	private String _valueParam;

}
