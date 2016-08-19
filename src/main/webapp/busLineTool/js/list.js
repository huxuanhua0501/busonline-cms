(function(){
	var  parentid=0,
	     indexid,dictionary;
	    var listShow=function(){
            $.ajax({
				url: 'http://192.168.109.227:40000/dict/selectDicItme',
				type: 'post',
				dataType: 'json',
				success: function(res) {
                  if(code==200){
                  	 var resData=res.data.one;
                  	 var html="";
                  	 var html2="";
                  	 for(var i=0;i<resData[i];i++){
                  	 	for(var j=0;j<resData[i].one.two.length;j++){
                  	 		html2+='<li><span>'+resData[i].two[j].dictionaryname+'</span><a href="javascript:;" class="xiugai" data-id="'+resData[i].two[j].id+'" data-level="2">修改</a><a href="javascript:;" '
                  	 		'class="delete" data-level="2" data-id="'+resData[i].two[j].id+'">删除</a></li>';
                  	 	}
                  html+='<tr><td>'+(i+1)+'</td><td>'+resData[i].dictionaryname+'<a href="javascript:;" class="xiugai" data-level="1" data-id="'+resData[i].id+'">修改</a><a href="javascript:;" class="delete" data-level="1" data-id="'+resData[i].id+
                  '">删除</a></td><td>'+'<ul class="liebiao_box">'+html2+'</ul></td></tr>';
                  html2="";
                  	 }
                  	 $(".tableList").append(html);
                  	 html="";

                  }
				}
			});
	    };
	    listShow();
		var update = function() {
			$.ajax({
				url: 'http://192.168.109.227:40000/dict/selectDicTwo',
				type: 'post',
				dataType: 'json',
				data: {
					parentid:indexnum,
					dictionaryname: dictionary
				},
				success: function(res) {
                  if(code==200){
                  	 alert("修改成功");
                  }
                  else{
                  	 alert("修改失败");
                  }
				}
			});
		};
       var deleteLevel2 = function() {
			$.ajax({
				url: 'http://192.168.109.227:40000/dict/delDicTwo',
				type: 'post',
				dataType: 'json',
				data: {
					parentid:indexnum
				},
				success: function(res) {
                  if(code==200){
                  	 alert("删除成功");
                  }
                  else{
                  	 alert("删除失败");
                  }
				}
			});
		};
		  var deleteLevel1 = function() {
			$.ajax({
				url: 'http://192.168.109.227:40000/dict/delDic',
				type: 'post',
				dataType: 'json',
				data: {
					parentid:indexnum
				},
				success: function(res) {
                  if(code==200){
                  	 alert("删除成功");
                  }
                  else{
                  	 alert("删除失败");
                  }
				}
			});
		};
		//删除二级索引
       $(".delete").click(function(){
       	   indexnum=$(this).attr("data-id");
       	   var level=$(this).attr("data-level");

			if (confirm("确定要删除吗？")) {
				if (level == 1) {
                    deleteLevel1();
				} else {
                    deleteLevel2();
				}
				
			}
       	   
       });
      //修改
      $(".xiugai").click(function(){
      	 indexnum=$(this).attr("data-id");
      	$(".updataWindow").show();
      });
       //修改保存
      $(".savaBtn").click(function(){
      	dictionary=$('.dictionary').val();
      	update();
      });
      $(".cancel").click(function(){
      	  $(".updataWindow").hide();
      });
})()
