  var user_Id = sessionStorage.getItem('userLofinId');
  var nickname = sessionStorage.getItem('userName');
var header='<div class="topbar"><div class="topbar_1"><span>公交数据采集系统</span><ul ><li class="caiji">百度数据采集<ul><li><a href="index.html"  title="">采集线路站点</a></li>'+
					'</ul></li><li class="shuju">已存数据管理<ul><li><a href="stationsLine.html"  title="">线路站点管理</a></li></ul></li><li class="yewu">基础配置 <ul><li><a href="city.html"  title="">城市管理</a></li>'+
							'<li><a href="guest.html"  title="">企业配置</a></li></ul> </li><li class="zidian">字典库<ul><li><a href="add.html"  title="">添加</a></li><li><a href="list.html"  title="">列表</a></li>'+
					'</ul></li><li class="zidian">账户管理 <ul><li><a href="admin.html"  title="">用户管理</a></li></ul></li><li>欢迎 '+nickname+'<a class="outLogin" onclick="out();">退出</a><a class="resetPassword" onclick="resetPsd();">密码重置</a></li></ul> </div>';
 $("body").before(header);


  function out() {
  
  	$.ajax({
  		url: '../user/logout.do',
  		type: 'post',
  		dataType: 'json',
  		data: {
  			userid: user_Id
  		},
  		success: function(res) {
  			if (res.code == 200) {	
  				window.location.href = "login.html";
  			} else {
  				alert("退出失败");
  			}
  		}
  	})
  };

  function resetPsd() {
  	$.ajax({
  		url: '../user/resetPsw.do',
  		type: 'post',
  		dataType: 'json',
  		data: {
  			userid: user_Id,
  			id:user_Id
  		},
  		success: function(res) {
  			if (res.code == 200) {
  				alert("重置成功");
  			} else {
  				alert("重置失败");
  			}
  		}
  	})
  };


