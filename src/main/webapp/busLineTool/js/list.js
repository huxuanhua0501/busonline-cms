(function(){
	var  $dictionaryname=$("#nameLevel1"),
	     dictionaryname;
	    var listShow=function(){
            $.ajax({
				url: 'http://192.168.109.227:40000/dict/selectDicItme',
				type: 'post',
				dataType: 'json',
				data: {
					parentid:0,
					dictionaryname: dictionaryname
				},
				success: function(res) {
                  if(code==200){
                  	 alert("添加成功");
                  }
				}
			});
	    };
		var addlevel = function() {
			$.ajax({
				url: 'http://192.168.109.227:40000/dict/insertdict',
				type: 'post',
				dataType: 'json',
				data: {
					parentid:0,
					dictionaryname: dictionaryname
				},
				success: function(res) {
                  if(code==200){
                  	 alert("添加成功");
                  }
				}
			});
		};

      //添加一级目录索引
      $(".add").click(function(){
      	 dictionaryname=$dictionaryname.val();
      	 if(dictionaryname){
      	 	 addlevel();
      	 }
      	 else{
      	 	alert("目录索引不能为空");
      	 }
      })
})()
