<div class="modal fade" id="lmsModal" tabindex="-1" role="dialog" aria-labelledby="lmsTitle" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="wealth-form-box text-center w-100"> 
                    <h4 class="text-center mb-3 error-msg">${title}</h4> 
                    <button class="edto-btn-primary m-auto" onclick="closeModal();">ok</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
if('${title}' != ''){
   $('#lmsModal').modal({backdrop: 'static', keyboard: false}, 'show');
}

function closeModal(){
	
	if('${title}' != ''){
		$('#lmsModal').modal('hide');
		Liferay.Util.navigate('/')
	}else{
		$('#lmsModal').modal('hide');
	}
	
}
</script>