(function(){
	var  parentid=0,
	     indexid,dictionary;
	    var listShow=function(){
	    	$(".first").nextAll().remove();
            $.ajax({
				url: '../dict/selectDicItme.do',
				type: 'post',
				dataType: 'json',
				data:{
					 userid:user_Id
					},
				success: function(res) {
                  if(res.code==200){
                  	 var resData=res.data.arr;
                  	 var html="";
                  	 var html2="";
					for (var i = 0; i < resData.length; i++) {
						for (var j = 0; j < resData[i].two.length; j++) {
							html2 += '<li><span>' + resData[i].two[j].dictionaryname + '</span><a href="javascript:;" class="xiugai" data-id="' + resData[i].two[j].id + '" data-level="2">修改</a><a href="javascript:;" '+
							'class="delete" data-level="2" data-id="' + resData[i].two[j].id + '">删除</a></li>';
						}
						html +='<tr><td>' + (i + 1) + '</td><td>' + resData[i].dictionaryname + '<a href="javascript:;" class="xiugai" data-level="1" data-id="' + resData[i].id + '">修改</a></td><td><ul class="liebiao_box">' + html2 + '</ul></td></tr>';
							// 	html +='<tr><td>' + (i + 1) + '</td><td>' + resData[i].dictionaryname + '<a href="javascript:;" class="xiugai" data-level="1" data-id="' + resData[i].id + '">修改</a><a href="javascript:;" class="delete" data-level="1" data-id="' + resData[i].id +
							// '">删除</a></td><td><ul class="liebiao_box">' + html2 + '</ul></td></tr>';
						html2 = "";
					}
                  	 $(".tableList").append(html);
                  	 html="";

                  }else if(res.code == 420){
					window.location.href="login.html";
				}
				}
			});
	    };
	    listShow();
		var update = function() {
			$.ajax({
				url: '../dict/updateDicname.do',
				type: 'post',
				dataType: 'json',
				data: {
					userid:user_Id,
					id:indexnum,
					dictionaryname: dictionary
				},
				success: function(res) {
                  if(res.code==200){
                  	 alert("修改成功");
                  	 listShow();
                  	 $(".updataWindow").hide();
                  	
                  	
                  }else if(res.code == 420){
					window.location.href="login.html";
				}
                  else{
                  	 alert("修改失败");
                  }
				}
			});
		};
       var deleteLevel2 = function() {
			$.ajax({
				url: '../dict/delDicTwo.do',
				type: 'post',
				dataType: 'json',
				data: {
					userid:user_Id,
					id:indexnum
				},
				success: function(res) {
                  if(res.code==200){
                  	 
                  	  alert("删除成功");
                  	   listShow();

                  }else if(res.code == 420){
					window.location.href="login.html";
				}
                  else{
                  	 alert("删除失败");
                  }
				}
			});
		};
		  var deleteLevel1 = function() {
			$.ajax({
				url: '../dict/delDic.do',
				type: 'post',
				dataType: 'json',
				data: {
					id:indexnum,
					userid:user_Id
				},
				success: function(res) {
                  if(res.code==200){
                  	 alert("删除成功");
                  	  listShow();
                  }else if(res.code == 420){
					window.location.href="login.html";
				}
                  else{
                  	 alert("删除失败");
                  }
				}
			});
		};
      //修改
      $(".tableList").on("click",'.xiugai',function(){
      	 indexnum=$(this).attr("data-id");
      	 $(".dictionary").val("");
      	 $(".updataWindow").show();
      });
       //修改保存
       $(".savaBtn").on("click",function(){
        	dictionary=$('.dictionary').val();
      	    update();
      });
      //删除索引
       $(".tableList").on("click",'.delete',function(){
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
      $(".cancel").click(function(){
      	  $(".updataWindow").hide();
      });
})()
