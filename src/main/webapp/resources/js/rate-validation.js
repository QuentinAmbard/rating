var RATING = RATING || {};
RATING.validation = function () {
	$('#editForm').validate();
	
	$('input[data-sum]').each(function () {
		var sumEl = $(this);
		var n = sumEl.attr("data-sum").replace(/'/g,'"') ;
		var names = jQuery.parseJSON(n);
		var year = sumEl.attr("data-year");
		$.each(names, function(i, name) {
			var el = $('#'+name+'_'+year);
			el.bind('keyup keypress blur change', function() {
				var sum=0;
				$.each(names, function(i, name) {
					var valInt = parseInt($('#'+name+'_'+year).val(),10);
					if (!isNaN(valInt)) {
						sum+=valInt;
					}
				});
				sumEl.val(sum);
				sumEl.change();
			});
		});

	});
	$('input[data-validation]').each(function () {
		var val = $(this).attr("data-validation").replace(/'/g,'"') ;
		var validation = jQuery.parseJSON(val);
		$(this).rules("add", validation);
	});
};

