function createBuilds(projectId, url){
	$.getJSON(url + projectId, function( data ) {
		$("#buildSelect").empty();
		var builds = document.getElementById('buildSelect');
		$.each( data, function( key, val ) {
		 	var option = document.createElement('option');
		 	option.value = val[0];
			option.innerHTML = val[1];
			builds.appendChild(option);
		});
	});
}
function changeStatus(status){
	var summary = document.getElementById('summary');
	var description = document.getElementById('description');
	var resolution = document.getElementById('resolutionSelect');
	var type = document.getElementById('typeSelect');
	var priority = document.getElementById('prioritySelect');
	var project = document.getElementById('projectSelect');
	var build = document.getElementById('buildSelect');
	var assignee = document.getElementById('assigneeSelect');
	if(assignee){
		assignee.disabled = (status === '1');
	}
	if(resolution) {
		resolution.disabled = !(status === '4' || status === '5');
	}
	if (summary.disabled) {
		summary.disabled = (status === '5');
		description.disabled = (status === '5');
		type.disabled = (status === '5');
		priority.disabled = (status === '5');
		project.disabled = (status === '5');
		build.disabled = (status === '5');
		assignee.disabled = (status === '5');
	}
}