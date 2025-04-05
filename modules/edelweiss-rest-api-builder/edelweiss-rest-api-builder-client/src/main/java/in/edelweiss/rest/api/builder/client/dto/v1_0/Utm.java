package in.edelweiss.rest.api.builder.client.dto.v1_0;

import in.edelweiss.rest.api.builder.client.function.UnsafeSupplier;
import in.edelweiss.rest.api.builder.client.serdes.v1_0.UtmSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author pramod.dk
 * @generated
 */
@Generated("")
public class Utm implements Cloneable, Serializable {

	public static Utm toDTO(String json) {
		return UtmSerDes.toDTO(json);
	}

	public String getAdgroupId() {
		return adgroupId;
	}

	public void setAdgroupId(String adgroupId) {
		this.adgroupId = adgroupId;
	}

	public void setAdgroupId(
		UnsafeSupplier<String, Exception> adgroupIdUnsafeSupplier) {

		try {
			adgroupId = adgroupIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String adgroupId;

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public void setCampaignId(
		UnsafeSupplier<String, Exception> campaignIdUnsafeSupplier) {

		try {
			campaignId = campaignIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String campaignId;

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public void setDevice(
		UnsafeSupplier<String, Exception> deviceUnsafeSupplier) {

		try {
			device = deviceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String device;

	public String getGclid() {
		return gclid;
	}

	public void setGclid(String gclid) {
		this.gclid = gclid;
	}

	public void setGclid(
		UnsafeSupplier<String, Exception> gclidUnsafeSupplier) {

		try {
			gclid = gclidUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String gclid;

	public String getUtmCampaign() {
		return utmCampaign;
	}

	public void setUtmCampaign(String utmCampaign) {
		this.utmCampaign = utmCampaign;
	}

	public void setUtmCampaign(
		UnsafeSupplier<String, Exception> utmCampaignUnsafeSupplier) {

		try {
			utmCampaign = utmCampaignUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String utmCampaign;

	public String getUtmContent() {
		return utmContent;
	}

	public void setUtmContent(String utmContent) {
		this.utmContent = utmContent;
	}

	public void setUtmContent(
		UnsafeSupplier<String, Exception> utmContentUnsafeSupplier) {

		try {
			utmContent = utmContentUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String utmContent;

	public String getUtmCreative() {
		return utmCreative;
	}

	public void setUtmCreative(String utmCreative) {
		this.utmCreative = utmCreative;
	}

	public void setUtmCreative(
		UnsafeSupplier<String, Exception> utmCreativeUnsafeSupplier) {

		try {
			utmCreative = utmCreativeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String utmCreative;

	public String getUtmMedium() {
		return utmMedium;
	}

	public void setUtmMedium(String utmMedium) {
		this.utmMedium = utmMedium;
	}

	public void setUtmMedium(
		UnsafeSupplier<String, Exception> utmMediumUnsafeSupplier) {

		try {
			utmMedium = utmMediumUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String utmMedium;

	public String getUtmPlacement() {
		return utmPlacement;
	}

	public void setUtmPlacement(String utmPlacement) {
		this.utmPlacement = utmPlacement;
	}

	public void setUtmPlacement(
		UnsafeSupplier<String, Exception> utmPlacementUnsafeSupplier) {

		try {
			utmPlacement = utmPlacementUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String utmPlacement;

	public String getUtmSource() {
		return utmSource;
	}

	public void setUtmSource(String utmSource) {
		this.utmSource = utmSource;
	}

	public void setUtmSource(
		UnsafeSupplier<String, Exception> utmSourceUnsafeSupplier) {

		try {
			utmSource = utmSourceUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String utmSource;

	public String getUtmTerm() {
		return utmTerm;
	}

	public void setUtmTerm(String utmTerm) {
		this.utmTerm = utmTerm;
	}

	public void setUtmTerm(
		UnsafeSupplier<String, Exception> utmTermUnsafeSupplier) {

		try {
			utmTerm = utmTermUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String utmTerm;

	@Override
	public Utm clone() throws CloneNotSupportedException {
		return (Utm)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Utm)) {
			return false;
		}

		Utm utm = (Utm)object;

		return Objects.equals(toString(), utm.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return UtmSerDes.toJSON(this);
	}

}