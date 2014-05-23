function createBuilds(projectId, url){
	$.getJSON(url, { 'projectId': projectId}, function( data ) {
		$("#buildsSelect").empty();
		var builds = document.getElementById('buildsSelect');
		$.each( data, function( key, val ) {
		 	var option = document.createElement('option');
		 	option.value = key;
			option.innerHTML = val;
			builds.appendChild(option);
		});
	});
}
function changeStatus(status){
	var assignee = document.getElementById('assigneeSelect');
	var resolution = document.getElementById('resolutionSelect');
	if(assignee){
		if (status === '1') {
			assignee.disabled = true;
		} else {
			assignee.disabled = false;
		}
	}
	if(resolution) {
		if (status === '4' || status === '5') {
			resolution.disabled = false;
		} else {
			resolution.disabled = true;
		}
	}
}