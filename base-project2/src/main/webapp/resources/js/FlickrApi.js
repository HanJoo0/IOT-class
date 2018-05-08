class FlickrApi {
	constructor () {
		this.url = 'https://api.flickr.com/services/rest/?jsoncallback=?';
		this.api_key = 'd52dfc7be69daf0370a99ea123c70054';
		this.format = 'json';
	}
	
	getRecent(parse) {
		parse = parse || this.parse;
		
		var param = {
			api_key : this.api_key,
			method : 'flickr.photos.getRecent',
			per_page : 20,
			format : this.format			
		};

		$.getJSON(this.url, param, parse);
	}
	search(keyword, parse) {
		parse = parse || this.parse;
		var param = {
			api_key : this.api_key,
			method : 'flickr.photos.search',
			// text : keyword,
			tags : keyword,
			content_type : 1,
			safe_search : 1,
			sort : 'interestingness-desc',
			per_page : 12,
			format : this.format			
		};
		$.getJSON(this.url, param, parse);
	}
	
	parse(data) {
		$('#panel').empty();
	
		$(data.photos.photo).each(function(i, photo){
			// 작은 이미지
			var img  =`http://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg`;
			// 이미지 정보 페이지
			var img_info =`http://www.flickr.com/photos/${photo.owner}/${photo.id}/in/photostream`;
			var templ = `
				<div class="col-md-4 mb-4">
					<div class="view zoom">
						<a href="${img_info}" target="_blank">
							<img src="${img}" width="200" 
									class="img-fluid ">
						</a>
						<div class="mask flex-center"></div>
					</div>					
				</div>
			`;
			$(templ).appendTo('#panel');
		});		
		
		
/*		$(data.photos.photo).each(function(i, photo){


		});	*/
	
	}
	
}
