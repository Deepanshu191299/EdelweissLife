var namespace=$("#portletNamespace").val();
debugger;

/**
 * Display the Product Riders and hide recommended products
 */
function showProductRiders() {
	$("#"+namespace+"recomendedProducts").hide();
	$("#"+namespace+"productRiders").show();
}

/**
 * Display the recommended products and hide Product Riders
 */
function showRecomendedProducts() {
	
	$("#"+namespace+"recomendedProducts").show();
	$("#"+namespace+"productRiders").hide();

};