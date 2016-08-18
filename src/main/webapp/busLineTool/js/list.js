(function(){
	var  parentid=0,
	     dictionaryname;
	    var listShow=function(){
            $.ajax({
				url: 'http://192.168.109.227:40000/dict/selectDicItme',
				type: 'post',
				dataType: 'json',
				success: function(res) {
                  if(code==200){
                  	 var resData=res.data;
                  	 var html="";
                  	 for(var i=0;i<resData[i];i++){
                  	 	html+='<tr><td>'+i+'</td><td>品牌名称<a href="javascript:;" class="xiugai">修改</a><a href="javascript:;">删除</a></td><td><ul class="liebiao_box">
					<li><span>伊利</span><input type="text" id="" class="dictionary"><a href="javascript:;" class="savaBtn">保存</a><a href="javascript:;" class="xiugai" data-id="'+i+'">修改</a><a href="javascript:;">删除</a></li>
				</ul>
			</td>
		</tr>';
                  	 }

                  }
				}
			});
	    };
		var update = function() {
			$.ajax({
				url: 'http://192.168.109.227:40000/dict/selectDicTwo',
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

      //修改
      $(".xiugai").click(function(){
      	var indexnum=$(this).attr("data-id");
      	$(".dictionary").eq(indexnum).show();
      	$(".savaBtn").eq(indexnum).show();
      	 dictionaryname=$(".dictionary").val();
      	 parentid=$(".dictionary").attr("id");
      	
      });
      $(".savaBtn").click(function(){
      	
      })
})()
