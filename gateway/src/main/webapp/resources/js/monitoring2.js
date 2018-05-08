
var templ = {
	location : function(location) {
		return $(`
		<div class="card">
			<div class="card-header">
				<h4 class="card-title">
			    	<a>${location}</a>
				</h4>
			</div>
			<div class="card-body p-1"></div>		
		</div>`); 
	} 
	,
	deviceGroup : function(groupName) {
		return $(`
		<div class="row">
			<div class="col-2" class="group-name text-right m-1">
				${groupName}
			</div>
			<div class="col-10" >
				<div class="row devices"">
				</div>
			</div>
		</div>`);
	},
	illumDevice :  function(dev) {
		var bright = rgb(dev.value);
		
		return $(`
			<div class="col-2 center m-1" 
				style="background:lightgray">
				<div class="text-center pt-3">
					<h3 class="mb-2"><i class="fas fa-sun" style="color:${bright}"></i></h3>
					<span class="small">${dev.value}</span>
				</div>
				<div class="text-center">${dev.name}</div>
			</div>
		`);
	},
	lightDevice :  function(dev) {
		var onOff = parseInt(dev.value) ? 
						'<i style="color:white" class="fas fa-lightbulb"></i>' :		// ON
						'<i class="fas fa-lightbulb"></i>';			// OFF
		return $(`
			<div class="col-2 center m-1" 
				style="background:lightgray">
				<div class="text-center pt-3"><h3  class="mb-2">${onOff}</h3></div>
				<div class="text-center">${dev.name}</div>
			</div>
		`);
	}
	
		
};


var templLight = {
		templOn : '<i style="color:white" class="fas fa-lightbulb"></i>',
		templOff : '<i class="fas fa-lightbulb"></i>',
		templ : function(dev) {
			var status = parseInt(dev.value) ?
						this.templOn : this.templ.Off;
			return $(`
				<div  class="center m-1" style="background:lightgray">
					<div class="text-center pt-3"><h3  class="mb-2">${status}</h3></div>
					<div class="text-center">${dev.name}</div>
				</div>`);
		}
}

$.fn.light = function(dev) {
	var self = this;
	self.append(templLight.templ(dev));
	return self;
}

var templLightSensor = {
		rgb : function(value) {
			value = parseInt(value);
			value = value > 80 ? 100 : value+10;
			var v = parseInt((255*value/100)).toString(16);
			return '#' + v + v + v;  
		}, 
		templ : function(dev) {
			var bright = this.rgb(dev.value);
			return $(`
				<div  class="center m-1" style="background:lightgray">
					<div class="text-center pt-3">
						<h3 class="mb-2"><i class="fas fa-sun" style="color:${bright}"></i></h3>
						<span class="small">${dev.value}</span>
					</div>
					<div class="text-center">${dev.name}</div>
				</div>`);
		}
};

$.fn.lightSensor = function(dev) {
	var self = this;
	self.append(templLight(dev));
	return self;
}




$.fn.monitoring = function() {
	var self = this;
	
	var socket = new SockJS("dashboard");
	socket.onopen = function() {
		console.log('접속 성공');
	}
	
	socket.onclose = function() {
		console.log('접속 해제');
	}
	
	function makeDevices(group, devices) {

		var arr = [];
		for(var dev in devices){
			arr.push(devices[dev]);
		}
		
		arr = arr.sort((a, b)=>{
			return a.name > b.name;
		});
		
		
		arr.forEach(function(dev){
			var devObj;
			if(dev.type=="조명") {	// 조명 
				devObj = templ.lightDevice(dev);
			} else {	// 조도 센서 
				devObj = templ.illumDevice(dev);
			}
			
			group.find(".devices").append(devObj)
		});
	}
	
	
	function makeGroup(location, deviceGroup) {
		for(var gname in deviceGroup){
			var group = templ.deviceGroup(gname);
			makeDevices(group, deviceGroup[gname].group)
			location.find('.card-body').append(group);
		}
	}
	
	
	
	socket.onmessage = function(msg) {
		var status = JSON.parse(msg.data)
		self.empty();
		for(var loc in status){
			// 최상위 카드(위치별)
			var location = templ.location(loc)
			var deviceGroup = status[loc].deviceGroup;
			makeGroup(location, deviceGroup);
			self.append(location);
		}
	}
	
	socket.onerror = function(err) {
		console.log('에러 ', err);
	}
	
}