var templLightSensor = {
	rgb : function(value) {
		value = parseInt(Math.ceil(value/20)*20);
		var v = parseInt((255*value/100)).toString(16);
		return '#' + v + v + v;  
	}, 
	templ : function(dev) {
		var bright = this.rgb(dev.value);
		console.log(dev.value, bright)
		return $(`
		<div  class="center m-1" style="background:lightgray">
			<div class="text-center pt-3">
				<h3 class="mb-2">
					<i class="fas fa-sun" style="color:${bright}"></i>
					<sup><small>${dev.value}</small></sup>
				</h3>
			</div>
			<div class="text-center">${dev.name}</div>
		</div>`);
	}
};
$.fn.lightSensor = function(dev) {
	var self = this;
	self.append(templLightSensor.templ(dev));
	return self;
}
//------------------------------------------------


var templLight = {
	templ : function(dev) {
		var status = parseInt(dev.value) ?
				'<i style="color:white" class="fas fa-lightbulb"></i>' : 
				'<i class="fas fa-lightbulb"></i>';
		return $(`
			<div  class="center m-1" style="background:lightgray">
				<div class="text-center pt-3">
					<h3  class="mb-2">${status}</h3>
				</div>
				<div class="text-center">${dev.name}</div>
			</div>`);
	}
}

$.fn.light = function(dev) {
	var self = this;	
	self.append(templLight.templ(dev));
	return self;
}
// --------------------------------------------------------------


function convertObjToArr(obj){
	var arr = [];
	for(var prop in obj){
		arr.push(obj[prop]);
	}
	return arr;
}

$.fn.deviceGroup = function(deviceGroup) {
	var self = this;
	self.find('.group-name').text(deviceGroup.groupName);
	
	convertObjToArr(deviceGroup.group) // 객체를 속성값 배열로 변경 
		.sort((a, b)=> a.name > b.name ? 1 :
						a.name < b.name ? -1 : 0)
		.forEach(function(device) {
		var obj = $('<div class="col-3"></div>');
		switch(device.type) {
			case '조도' : 
				obj.lightSensor(device);
				break;
			case '조명' : 
				obj.light(device);
				break;
		}
		self.find('.devices').append(obj)
	});
	return self;
}

//------------------------------------------------
var templDeviceGroup = 	`
	<div class="row  m-1">
		<div class="col-2 group-name text-right"></div>
		<div class="col-10" >
			<div class="row devices"></div>
		</div>
	</div>`;

$.fn.location = function(location) {
	var self = this;
	
	self.find('.location').text(location.location);
	for(var groupName in location.deviceGroup) {
		var group = location.deviceGroup[groupName];
		// deviceGroup 플러그인 적용
		var groupObj = $(templDeviceGroup).deviceGroup(group)
		self.find('.device-groups')
			.append(groupObj);	
	}
	return this;
}

var templLocation = `
	<div class="card col-6 mb-2">	
		<div class="card-header">
			<h4 class="card-title">
		    	<a class="location"></a>
			</h4>
		</div>
		<div class="card-body p-1 device-groups">
		</div>
	</div>`; 

$.fn.monitoring = function() {
	var self = this;
	
	var socket = new SockJS("dashboard");
	socket.onopen = ()=>console.log('접속 성공');
	socket.onclose= ()=>console.log('접속 해제');
	socket.onerror = err=>console.log('에러 ', err);
	
	socket.onmessage = function(msg) {
		var locations = JSON.parse(msg.data)
		self.empty();
		for(var loc in locations){
			var location = $(templLocation)
								.location(locations[loc]);
			self.append(location);
		}
	}
	return self;
}