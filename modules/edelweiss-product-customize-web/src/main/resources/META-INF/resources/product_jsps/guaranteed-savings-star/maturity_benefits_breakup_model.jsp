<%@ include file="/init.jsp"%>
<div class="modal maturitybenefittable" tabindex="-1" role="dialog" id="planMaturityBenefitsBreakup" style="display: none;">
	<div class="modal-dialog modal-dialog-centered popup-container" role="document">
		<div class="modal-content maturity-benefit-wrapper">
			<div class="modal-header">
				<h2 class="modal-title"><liferay-ui:message key="label-maturity-benefits" /></h2>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
			<div class="table-wrap">
				<table  class="maturity-table table table-head scrl-tbl-w table-border" width="100%" id="planMaturityBenefitsBreakupTable">
				 <thead style="display: table;"> 
			        <tr>
			            <td>Policy Year</td>
			            <td>Annualized Premium</td>
			            <td>Maturity Benefit (INR)</td>
			            <td>Death Benefit</td>
			        </tr>
			        </thead>
			        <tbody class="scrl-tbl-hgt m-cust-scroll maturity-data-table" id="planMaturityBenefitsBreakupTableBody">
			        </tbody>
				</table>
			</div>
			</div>
		<!-- 	<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal"><liferay-ui:message key="ok" /></button>
			</div> 
			-->
		</div>
	</div>
</div>

<style>

.maturitybenefittable  .modal.show .modal-dialog {
    -webkit-transform: translate(0,0);
    transform: translate(0,0);
}
.maturity-benefit-wrapper {
    padding: 20px;
    border: 0;
    min-width: 690px;
}
.maturity-benefit-wrapper .modal-header {
    border: 0;
    position: relative;
}
.maturity-benefit-wrapper .modal-body{
border:0px
}
.maturity-benefit-wrapper .modal-title {
    font-family: Montserrat-Bold;
    font-size: 22px;
    color: #3c3c3c;
    font-weight:600;
}

.maturitybenefittable  .table-wrap {
    display: block;
    width: 100%;
    overflow-x: auto;
    /* height: 300px; */
}
.maturitybenefittable  .table-border {
    border: 1px solid #f1f1f1;
}
.maturitybenefittable  .table {
    border: 1px;
    border-collapse: separate;
    border-left: 0;
    border-spacing: 0;
    max-width: 100%;
    margin-bottom: 1rem;
    background-color: transparent;
}
.maturitybenefittable  .table-head {
    border: 1px;
    border-collapse: separate;
    border-left: 0;
    border-top-right-radius: 10px;
    border-top-left-radius: 10px;
    border-spacing: 0;
    border: 1px solid #f1f1f1;
}
.maturitybenefittable  .maturity-table {
    border: 1px solid #f1f1f1;
    border-collapse: separate;
    border-radius: 10px 10px 0 0;
    width: 100%;
    text-align: center;
}
.maturitybenefittable .scrl-tbl-w .scrl-tbl-hgt {
    height: 200px;
    overflow-y: auto;
}
.maturitybenefittable .maturity-table tbody td {
    color: #343434;
    font-size: 13px;
    padding: 7px 4px;
    vertical-align: middle;
    font-family: Montserrat-Medium;
    border-top: 1px solid #f1f1f1;
    border-bottom: 1px solid #f1f1f1;
}
.maturitybenefittable   .table-border thead td:first-child, .maturitybenefittable  .table-border thead th:first-child {
    border-top: 1px solid #f1f1f1;
    border-radius: 10px 0 0;
}
.maturitybenefittable .maturity-table thead th:first-child {
    border-radius: 10px 0 0;
}

.maturitybenefittable .maturity-table thead tr td {
    border-bottom-width: 1px;
    background-color: #f1f1f1;
    color: #989898;
    font-size: 13px;
    /* width: 100px; */
    padding: 8px 22px;
    vertical-align: middle;
    border-bottom: 1px solid #f1f1f1;
    font-family: Montserrat-Medium;
}
.maturitybenefittable .maturity-table .table-border thead tr td {
    border-bottom-width: 1px;
    background-color: #f1f1f1;
    color: #989898;
    font-size: 13px;
    vertical-align: middle;
}

.maturitybenefittable tbody.scrl-tbl-hgt.m-cust-scroll.maturity-data-table {
    max-height: 200px;
    display: block;
    width: 100%;
}

.maturitybenefittable thead {
    display: table;
}
.maturitybenefittable .scrl-tbl-w .scrl-tbl-hgt {
    display: block;
    max-height: 200px;
    overflow-y: auto;
}
.maturitybenefittable .scrl-tbl-hgt tr {
    display: table;
    width: 100%;
    table-layout: fixed;
}
.maturitybenefittable .maturity-table thead tr td:last-child {
    border-radius: 0 10px 0 0;
}
.maturitybenefittable .popup-container {
    margin: auto;
    max-width: 640px;
    min-width: 640px;
}

.maturity-benefit-wrapper .close span {
    font-size: 40px;
    font-weight: 100;
}
</style>
