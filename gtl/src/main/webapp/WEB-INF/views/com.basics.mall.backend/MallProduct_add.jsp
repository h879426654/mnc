<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		 <meta name="decorator" content="backend" />
		 <meta charset="UTF-8">
	    <title>商品添加</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	    <meta name="format-detection" content="telephone=no">
		<script src="${pageContext.request.contextPath}/assets/jquery/dist/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/cropper.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/kindeditor/kindeditor.js"></script>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cropper.min.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ImgCropping.css">
		 <style>
		    html{font-size: 16px;}
		    div,p,ul,li,textarea,input,select{padding:0;margin:0;}
		 	ul,li{list-style: none;}
		 	em,i{font-style:normal;}
			p{color:#63baff;line-height: 30px;font-size: 12px;}
			textarea,input ,select{outline:none;font-size: 16px;}
			img{display: block;width: 100%;height: 100%;}
			.rightDatalei li div:after,
			.clear:after{content:"";display:block;clear:both;}
			
			.dataTable{background:#eee;width: 1200px;margin: 10px auto;}
			.dataTable>li{position: relative;padding:10px 0;padding-left: 120px;}
			.dataTable li .leftText{position: absolute;left: 0;top:0;line-height: 40px;width: 110px;text-align: right;}
			.rightDataImg li{float:left;margin-right: 10px;position: relative;}
			.rightDataImg li button{position: absolute;top:0;left: 0;width: 100%;height: 100%;background: none;border:none;}
			.textareas{margin-right: 10px;border:1px solid #999;padding:10px;background: #fff;}
			.textareas textarea{border:none;width: 100%;position: relative;}
			.textareas span{position: absolute;right: 20px;bottom: 10px;line-height: 20px;font-size: 12px;color:#999;}
			.rightDatainput li{float:left;width: 40%;}
			.rightDatainput li input{line-height: 38px;padding:0 10px;margin-right: 10px;box-shadow:  0 0 1px #999;}
			.rightDatainput li i{vertical-align:top;}
			.rightDatainput li span{color:#999;}
			.rightDataSelect li{width: 40%;float:left;}
			.rightDataSelect li select{width: 220px;height: 38px;padding:0 10px;}
			.rightDataSelect li span{color:#999;}
			.rightDatalei>li>span{display: block;line-height: 28px;background: #fff;border:1px solid #63baff;width: 100px;text-align: center;margin:10px;font-size: 14px;}
			.rightDatalei>li>div{background: #fff;padding:10px;padding-bottom: 0;border-top:1px solid #ccc;}
			.rightDatalei>li{margin-right: 10px;border:1px solid #999;margin-bottom: 10px;position: relative;}
			.rightDatalei>li>i{position: absolute;top:-15px;right: -15px;width: 30px;height: 30px;line-height: 30px;text-align: center;border-radius: 50%;background: #fff;box-shadow: 0 0 1px #999;cursor: pointer;}
			.rightDatalei li div ul{float:left;}
			.rightDatalei li div ul li{float:left;padding:0 20px;margin-right: 20px;background: #66baff;margin-bottom: 10px;line-height: 30px;color:#fff;position: relative;font-size: 14px;}
			.rightDatalei li div div{float:left; margin-bottom: 10px;}
			.rightDatalei li div ul li i{position: absolute;top:-8px;right: -8px;background: #999;height: 16px;width: 16px;line-height: 16px;text-align: center;border-radius: 50%;cursor: pointer}
			.rightDataleijia{margin-top:30px;}
			.rightDataleijia input,
			.rightDatalei li div div input{width: 100px;line-height: 28px;padding:0 10px;box-shadow:  0 0 1px #999;border:1px solid #999;}
			.rightDataleijia span,
			.rightDatalei li div div span{cursor: pointer;color:#fff;background: #66baff;display: inline-block;;line-height: 30px;padding:0 5px;}
			.rightDatalei li div div i{color:red;}
			.btns{display: block;margin:10px auto;padding:5px 20px;cursor: pointer}
			.btnsData{padding:10px;background:#eee;width: 1180px;margin: 10px auto;}
			.btnsData li{float:left;padding:10px;padding-left: 140px;position: relative;height: 140px;background: #fff;margin-right: 10px;width:32%;margin-bottom: 10px;}
			.btnsData li:nth-child(3n){margin-right: 0;}
			.btnsData li>div:first-child{width: 120px;position: absolute;top:10px;left: 10px;box-shadow:  0 0 1px #999;}
			.btnsData li>div>button{position: absolute;top:0;left:0;width: 120px;height: 120px;border:none;background: none;}
			.btnsData li span{color:#66baff;}
			.btnsData li s{display: block;width: 0px;height: 0px;overflow: hidden;}
			.btnsData li div:not(:first-child){margin-top:10px;}
			.btnsData li div:not(:first-child) input{width: 100px;padding:0 10px;margin-left: 10px;box-shadow:  0 0 1px #999;}
			.btnsData>li>i{position: absolute;top:-5px;right: -5px;background: #fff;height: 30px;width: 30px;line-height: 30px;text-align: center;border-radius: 50%;cursor: pointer;color:#999;box-shadow: 0 0 1px #666;}
			.dataTable h3{padding:10px;border-left: 5px solid #63baff;}
			.finalImg234{width:0px;height:0px;overflow:hidden}
			.reds{color:red;}
		 </style>
		 <script type="text/javascript">
		 $(function() {
			 KindEditor.ready(function(K) {  
				 window.editor = K.create('#productContext',{
		        	 resizeType : 1,
		        	 allowPreviewEmoticons : false,
		             allowImageUpload : true,
		             uploadJson : 'kindEditorFileUpload.do',
		             items : [
		                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
		                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
		                    'insertunorderedlist', 'image','|', 'emoticons', 'link']	 
		         });
		     });  
			 
			//弹出框水平垂直居中
			    (window.onresize = function () {
			        var win_height = $(window).height();
			        var win_width = $(window).width();
			        if (win_width <= 768){
			            $(".tailoring-content").css({  "top": (win_height - $(".tailoring-content").outerHeight())/2, "left": 0 });
			        }else{
			            $(".tailoring-content").css({  "top": (win_height - $(".tailoring-content").outerHeight())/2,  "left": (win_width - $(".tailoring-content").outerWidth())/2 });
			        }
			    })();

			    //弹出图片裁剪框
			    var types=0;
			   // $("body").on("click","#replaceImg", function () { $(".tailoring-container").toggle(); types=1; });
			    $("body").on("click","#replaceImg1",function () { $(".tailoring-container").toggle(); types=2; });
			    $("body").on("click","#replaceImg2",function () { $(".tailoring-container").toggle(); types=3; });
			    $("body").on("click","#replaceImg3",function () { $(".tailoring-container").toggle(); types=4; });
			    $("#replaceImg").click(function(){
			    	$(".tailoring-container").toggle(); 
			    	types=1;
			    }
			    	
			    	 );

			    $(".btnsData").on("click","li>div>button",function(){
			    	var list =$(this)[0].id;
			    	list=list.split("replaceImg");
			    	$(".tailoring-container").toggle();
			    	types=list[1];
			    })

			   
			    //cropper图片裁剪
			    $('#tailoringImg').cropper({
			        aspectRatio: 1/1,//默认比例
			        preview: '.previewImg',//预览视图
			        guides: false,  //裁剪框的虚线(九宫格)
			        autoCropArea: 0.5,  //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
			        movable: false, //是否允许移动图片
			        dragCrop: true,  //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
			        movable: true,  //是否允许移动剪裁框
			        resizable: true,  //是否允许改变裁剪框的大小finalImg
			        zoomable: true,  //是否允许缩放图片大小
			        mouseWheelZoom: true,  //是否允许通过鼠标滚轮来缩放图片
			        touchDragZoom: true,  //是否允许通过触摸移动来缩放图片
			        rotatable: true,  //是否允许旋转图片
			        crop: function(e) {   }// 输出结果数据裁剪图像。
			    });
			    //裁剪后的处理
			    $("#sureCut").on("click",function () {
			        if ($("#tailoringImg").attr("src") == null ){  return false; }else{
			            var cas = $('#tailoringImg').cropper('getCroppedCanvas');//获取被裁剪后的canvas
			            var base64url = cas.toDataURL('image/png'); //转换为base64地址形式

			          /*  if(types==1){
			            	 $("#finalImg1").prop("src",base64url);//显示为图片的形式
			            }else if(types==2){
			            	 $("#finalImg2").prop("src",base64url);//显示为图片的形式
			            }else if(types==3){
			            	 $("#finalImg3").prop("src",base64url);//显示为图片的形式
			            }else if(types==4){
			            	 $("#finalImg4").prop("src",base64url);//显示为图片的形式
			            }*/
			            var divs ="#finalImg"+types;
			            var classs=".finalImg"+types;
			          

			           $.post("${pageContext.request.contextPath}/api/common/uploadImagesByBase64.do",{"baseString":base64url},function(result){
			           		$(divs).prop("src",(result.data)[0]);
			           	 	$(classs).text((result.data)[0]);
			           	}); 
			            //关闭裁剪框
			            closeTailor();
			        }
			    });
			    /*********************************************/
			    //fave2
			    var optionhtml1="";
			    var optionhtml2="";
			     $.post("${pageContext.request.contextPath}/backend/mall/mallProductClassify/firstClassify.do",function(result){
			           		var arr=result.data;
			           		optionhtml1=`<option value="" selected label=<spring:message code="product.add.select" />><spring:message code="product.add.select" /></option>`;
			           		for(var i=0;i<arr.length;i++){
						        optionhtml1+=`<option value="`+arr[i].id+`" label="`+arr[i].classifyName+`">`+arr[i].classifyName+`</option>`;
			           		}
			           		$("#fave2").html(optionhtml1);
			           		
			           	}); 
			     $('#fave2').change(function(){  
			   　　　　　　var ids = $(this).children('option:selected').val();  
			 		  console.log(ids)
					   $.post("${pageContext.request.contextPath}/backend/mall/mallProductClassify/newSecondClassify.do", {productFirstClassify:ids},function(result){
			           		var arr = result;
			           		optionhtml2="";
			           		for(var i=0;i<arr.length;i++){
						          if(i==0){
						        	  optionhtml2=`<option value="`+arr[i].id+`" selected label="`+arr[i].classifyName+`">`+arr[i].classifyName+`</option>`;
						          }else{
						        	  optionhtml2+=`<option value="`+arr[i].id+`" label="`+arr[i].classifyName+`">`+arr[i].classifyName+`</option>`;
						          }
			           			
			           		}
			           		$("#fave3").html(optionhtml2);
			           		
			           	}); 
			   　　 	}); 
			    /******************************************************************/
			    $(".btntianjiaguige").on("click",function(){
			    	var htmls="";
			    	var arr=$(".guige")[0].value;
			    	if(arr!=""){
						
						//$(".rightDatalei")
						 htmls=`<li><i>x</i><span>`+arr+`</span><div><ul></ul><div class="btnbtnshows"><input type="text" placeholder=<spring:message code="product.add.kind.child.placeholder" />><span><spring:message code="product.add.kind.child.name" /></span><i><spring:message code="product.add.kind.child.eg" /></i></div></div></li>`;
						 if($(".rightDatalei>li").length==0){
						 	$(".rightDatalei").html(htmls);
						 }else if($(".rightDatalei>li").length<3){
						 	$(".rightDatalei>li:last-child").after(htmls);
						 }
						 
						$(".guige")[0].value="";
						if($(".rightDatalei>li").length==3){
							$(".rightDataleijia").hide();
						}
			    	}
			    });
			    $(".rightDatalei").on("click","li>div>div>span",function(){
			    	var arr=$(this).parent().find("input")[0].value
			    	if(arr!=""){
			    		//console.log($(this).parent().parent().find("ul").find("li")[$(this).parent().parent().find("ul").find("li").length-1])
			    		let	htmls=`<li><em>`+arr+`</em><i>x</i></li>`;
			    		if($(this).parent().parent().find("ul").html()==''){
			    			$(this).parent().parent().find("ul").html(htmls);
			    		}else if($(this).parent().parent().find("ul").find("li").length<8){
			    			$(this).parent().parent().find("ul").find("li:last-child").after(htmls);
			    		}
			    		$(this).parent().find("input")[0].value="";
			    		if($(this).parent().parent().find("ul").find("li").length==8){
			    			$(this).parent().hide();
			    		}
			    	}
			    });
			     $(".rightDatalei").on("click","li>div>ul>li>i",function(){
			    	$(this).parent().remove();  	
			    });
			      $(".rightDatalei").on("click","li>i",function(){
			    	$(this).parent().remove();
			    	//if($(".rightDatalei>li").length<4){
					$(".rightDataleijia").show();
					//	}

			    });
			      var guige=[
			      	{id:uuid(),name:"",order:1,kindDetail:[]},
			      	{id:uuid(),name:"",order:2,kindDetail:[]},
			      	{id:uuid(),name:"",order:3,kindDetail:[]}
			      	];
			      	//{name:"",type:[]},
			      $(".btnliebiao").on("click",function(){
			      	for(var i=0;i<$(".rightDatalei>li").length;i++){
			      		var arr=$(".rightDatalei>li")[i].children[2].children[0].children.length;
			      		guige[i].name=$(".rightDatalei>li")[i].children[1].innerHTML;
			      		for(var y=0;y<arr;y++){
			      			console.log($(".rightDatalei>li")[i].children[2].children[0].children[y].children[0].innerHTML)
			      			let nums=$(".rightDatalei>li")[i].children[2].children[0].children[y].children[0].innerHTML;
			      			guige[i].kindDetail.push({id:uuid(),value:nums});
			      		}

			      	}
			      
			       /***********************/
			       //<li><div><div><img id="finalImg3" src="${pageContext.request.contextPath}/image/add.jpg" width="100%"></div>
				       		//			<button id="replaceImg3"></button></div><span>红色*L</span><div><em>库存:</em><input type="text"></div></li>"
			       //
			       		var htmlImg=""
			       		var num=5;
				       if(guige[0].name==''){
				       		
				       }else{
				       		if(guige[1].name==''){
				       				if(guige[0].kindDetail.length!=0){
					       				for(var i=0;i<guige[0].kindDetail.length;i++){
					       					num+=1;
					       					htmlImg+=`<li>
							       						<div>
							       							<div><img id="finalImg`+num+`" src="${pageContext.request.contextPath}/image/add.jpg" width="100%"></div>
							       							<s class="finalImg`+num+`"></s>
							       							<button id="replaceImg`+num+`"></button>
							       						</div>
							       						<i>x</i>
							       						<span>`+guige[0].name+`:`+guige[0].kindDetail[i].value+`</span>
							       						<s>`+guige[0].kindDetail[i].id+`</s>
							       						<div><em><spring:message code="product.add.stock" />:</em><input type="text"></div>
							       						<div><em><spring:message code="product.add.freight.message" />:</em><input type="text"></div>
							       					</li>`
					       				}
					       				$(".baocun button").show();
					       				$(".rightDatalei i").hide();
					       				$(".btnliebiao").hide();
					       				$(".btnbtnshows").hide();
					       				$(".btnsData").html(htmlImg);
				       				}
					       	}else{

					       		if(guige[2].name==''){
					       			if(guige[1].kindDetail.length!=0){
						       			for(var i=0;i<guige[0].kindDetail.length;i++){
						       				for(var y=0;y<guige[1].kindDetail.length;y++){
						       					num+=1;
						       					htmlImg+=`<li>
						       						<div>
						       							<div><img id="finalImg`+num+`" src="${pageContext.request.contextPath}/image/add.jpg" width="100%"></div>
						       							<s class="finalImg`+num+`"></s>
						       							<button id="replaceImg`+num+`"></button>
					       							</div>
					       							<i>x</i>
					       							<span>`+guige[0].name+`:`+guige[0].kindDetail[i].value+` - 
					       								  `+guige[1].name+`:`+guige[1].kindDetail[y].value+`</span>
					       							<s>`+guige[0].kindDetail[i].id+`/`+guige[1].kindDetail[y].id+`</s>
					       							<div><em><spring:message code="product.add.stock" />:</em><input type="text"></div>
					       							<div><em><spring:message code="product.add.freight.message" />:</em><input type="text"></div>
					       						</li>`
						       				}
					       				}
					       				$(".baocun button").show();
					       				$(".rightDatalei i").hide();
					       				$(".btnliebiao").hide();
					       				$(".btnbtnshows").hide();
					       				$(".btnsData").html(htmlImg);
					       			}else{
					       				for(var i=0;i<guige[0].kindDetail.length;i++){
					       					num+=1;
					       					htmlImg+=`<li>
							       						<div>
							       							<div><img id="finalImg`+num+`" src="${pageContext.request.contextPath}/image/add.jpg" width="100%"></div>
							       							<s class="finalImg`+num+`"></s>
							       							<button id="replaceImg`+num+`"></button>
							       						</div>
							       						<i>x</i>
							       						<span>`+guige[0].name+`:`+guige[0].kindDetail[i].value+`</span>
							       						<s>`+guige[0].kindDetail[i].id+`</s>
							       						<div><em><spring:message code="product.add.stock" />:</em><input type="text"></div>
							       						<div><em><spring:message code="product.add.freight.message" />:</em><input type="text"></div>
							       					</li>`
					       				}
					       				$(".baocun button").show();
					       				$(".rightDatalei i").hide();
					       				$(".btnliebiao").hide();
					       				$(".btnbtnshows").hide();
					       				$(".btnsData").html(htmlImg);
					       			}
					       		}else{
					       			if(guige[2].kindDetail.length!=0){
						       			for(var i=0;i<guige[0].kindDetail.length;i++){
						       				for(var y=0;y<guige[1].kindDetail.length;y++){
						       					for(var x=0;x<guige[2].kindDetail.length;x++){
						       						num+=1;
						       						htmlImg+=`<li>
						       							<div>
						       								<div>
						       									<img id="finalImg`+num+`" src="${pageContext.request.contextPath}/image/add.jpg" width="100%">
						       								</div>
						       								<s class="finalImg`+num+`"></s>
					       									<button id="replaceImg`+num+`"></button>
					       								</div>
					       								<i>x</i>
					       								<span>`+guige[0].name+`:`+guige[0].kindDetail[i].value+` - 
					       									  `+guige[1].name+`:`+guige[1].kindDetail[y].value+` - 
					       									  `+guige[2].name+`:`+guige[2].kindDetail[x].value+`</span>
					       								<s>`+guige[0].kindDetail[i].id+`/`+guige[1].kindDetail[y].id+`/`+guige[2].kindDetail[x].id+`</s>
					       								<div><em><spring:message code="product.add.stock" />:</em><input type="text"></div>
					       								<div><em><spring:message code="product.add.freight.message" />:</em><input type="text"></div>
					       							</li>`
						       					}
						       					
						       				}
					       				}
					       				$(".baocun button").show();
					       				$(".rightDatalei i").hide();
					       				$(".btnbtnshows").hide();
					       				$(".btnliebiao").hide();
					       				$(".btnsData").html(htmlImg);
					       			}else{
					       				for(var i=0;i<guige[0].kindDetail.length;i++){
						       				for(var y=0;y<guige[1].kindDetail.length;y++){
						       					num+=1;
						       					htmlImg+=`<li>
						       						<div>
						       							<div><img id="finalImg`+num+`" src="${pageContext.request.contextPath}/image/add.jpg" width="100%"></div>
						       							<s class="finalImg`+num+`"></s>
						       							<button id="replaceImg`+num+`"></button>
					       							</div>
					       							<i>x</i>
					       							<span>`+guige[0].name+`:`+guige[0].kindDetail[i].value+` - 
					       								  `+guige[1].name+`:`+guige[1].kindDetail[y].value+`</span>
					       							<s>`+guige[0].kindDetail[i].id+`/`+guige[1].kindDetail[y].id+`</s>
					       							<div><em><spring:message code="product.add.stock" />:</em><input type="text"></div>
					       							<div><em><spring:message code="product.add.freight.message" />:</em><input type="text"></div>
					       						</li>`
						       				}
					       				}
					       				$(".baocun button").show();
					       				$(".rightDatalei i").hide();
					       				$(".btnliebiao").hide();
					       				$(".btnbtnshows").hide();
					       				$(".btnsData").html(htmlImg);
					       			}
					       		}
					       	}
				       }
			      })
				$(".btnsData").on("click","li>i",function(){
					$(this).parent().remove(); 
				})
				var imgDemoPath = $("#imgDemoPath")[0].currentSrc;
				var typelist=[]
				$(".baocun button").on("click",function(){
					typelist=[];
					for(var i=0;i<$(".btnsData li").length;i++){
						let ids =$(".btnsData li")[i].children[3].innerHTML;
						let val1=$(".btnsData li")[i].children[4].children[1].value;
						let val2=$(".btnsData li")[i].children[5].children[1].value;
						let imgs=$(".btnsData li")[i].children[0].children[1].innerHTML;
						if(val1==""||val2==""||imgs==""){
							CrudApp.alert("请完善规格信息");
							return;
						}
						typelist.push({
							combinationStr:ids,
							combinationStock:val1,
							combinationPrice:val2,
							combinationImg:imgs
						})
					}
					//商品名
					//console.log($(".productNametext")[0].value)
					//banerimg
					//console.log($("#finalImg1")[0].currentSrc)
					//文本
					//console.log(editor.html())
					//
					console.log($("#finalImg2")[0].currentSrc)
					
					//商品规格信息:
					//console.log(guige);
					//商品库存&价格
					//console.log(typelist)
					//商品规格信息:
					var guigeData=[]
					for(var i=0;i<guige.length;i++){
						if(guige[i].kindDetail.length!=0){
							guigeData.push(guige[i])
						}
					}
					var kindsJsonArrayStr = JSON.stringify(guigeData);
					var strTmp = JSON.stringify(typelist);
					var ban3=[];
					for(i=0;i<$(".finalImg234 s").length;i++){
						var list=$(".finalImg234 s")[i].innerHTML;
						if(null != list) {
							ban3.push(list)
						}
					};
					var ban3JsonStr = JSON.stringify(ban3);
					
					//表单验证
					if(ban3.length==0){ban3JsonStr=imgDemoPath;CrudApp.alert("商品轮播图至少有一张");return;};
					var productNames=$(".productNametext")[0].value;
					if(productNames==""){CrudApp.alert("商品名称不能为空");return;}
					//var productPrices=$(".productPrice")[0].value;
					var productImgs=$("#finalImg1")[0].currentSrc;
					if(productImgs==imgDemoPath){CrudApp.alert("商品封面图不能为空");return;}
					//if(productPrices==""){CrudApp.alert("商品价格不能为空");return;}
					var productCosts=$(".productCost")[0].value;
					if(productCosts==""){CrudApp.alert("商品成本价不能为空");return;}
					var productFreights=$(".productFreight")[0].value;
					if(productFreights==""){CrudApp.alert("商品运费不能为空");return;}
					var productFirstClassifys=$("#fave2").children('option:selected').val();
					if(productFirstClassifys==""){CrudApp.alert("商品一级 分类不能为空");return;}
					var productSecondClassifys=$("#fave3").children('option:selected').val();
					if(productSecondClassifys==""){CrudApp.alert("商品二级分类不能为空");return;}
					$.post("${pageContext.request.contextPath}/backend/mall/mallProduct/addProduct2.do",{
						productId:uuid(),
						productName:$(".productNametext")[0].value,
						productImg:$("#finalImg1")[0].currentSrc,
						productContext:editor.html(),
						productPrice:0,
						productCost:$(".productCost")[0].value,
						productFreight:$(".productFreight")[0].value,
						productFirstClassify :$("#fave2").children('option:selected').val(),
						productSecondClassify:$("#fave3").children('option:selected').val(),
					    kinds:kindsJsonArrayStr,
					 	combinations:strTmp,
			      	    carouselImg:ban3JsonStr
					 } ,function(result){
						 var resultJson = JSON.parse(result); 
						 if(resultJson.success) {
				           	window.location.href = '${pageContext.request.contextPath}/backend/mall/mallProduct/index.do';
						 } else {
							 CrudApp.alert(resultJson.message);
						 }
			         }); 
	
				})

			 
		});
		 
	   //图像上传
	   var selectImg = function(file) {
	        if (!file.files || !file.files[0]){  return; }
	        var reader = new FileReader();
	        reader.onload = function (evt) {
	            var replaceSrc = evt.target.result;
	            //更换cropper的图片
	            $('#tailoringImg').cropper('replace', replaceSrc,false);//默认false，适应高度，不失真
	        }
	        reader.readAsDataURL(file.files[0]);
	    }
	   
	 //关闭裁剪框
	  function closeTailor() {  $(".tailoring-container").toggle(); }
	   
	   
	 /**
	  * 生成UUID
	  * @returns {string}
	  */
	 function uuid() {
	  var s = [];
	  var hexDigits = "0123456789abcdef";
	  for (var i = 0; i < 36; i++) {
	   s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
	  }
	  s[14] = "4";
	  s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
	  s[8] = s[13] = s[18] = s[23] = "-";
	  var uuid = s.join("");
	  return uuid.split('-').join('');
	 } 
		 
		 
		 </script>
 	</head>
	<body>
		<div class="dataTable">
			<h3><spring:message code="product.add.title" /></h3>
		</div>
		<ul class="dataTable">
			<li>
				<span class="leftText"><spring:message code="product.add.name" />:</span>
				<div class="rightData textareas">
					<textarea rows="2" name="productName"  class="productNametext" maxlength="50" onchange="this.value=this.value.substring(0, 50)" onkeydown="this.value=this.value.substring(0, 50)" onkeyup="this.value=this.value.substring(0, 50)" ></textarea>
					<span><spring:message code="product.add.name.message" /></span>
				</div>
			</li>
			<li>
				<span class="leftText"><spring:message code="product.add.img" />:</span>
				<div class="rightData">
					<ul class="rightDataImg clear">
						<li>
							<div style="width: 120px;height: 120px;"><img id="finalImg1" src="${pageContext.request.contextPath}/image/add.jpg" width="100%"></div>
							<button id="replaceImg"></button>
						</li>
						<li><i class="reds"><spring:message code="product.add.img.message" /></i></li>
					</ul>
					
				</div>
			</li>
			<li>
				<span class="leftText"><spring:message code="product.add.context" />:</span>
				<div class="rightData">
					<textarea style="width: 990px; height: 200px" required="required" data-role="kindeditor" class="kindeditor" name="productContext" id="productContext"></textarea>
				</div>
			</li>
			<img id="imgDemoPath" src="${pageContext.request.contextPath}/image/add.jpg" width="100%" style="display:none;">
			<li>
				<span class="leftText"><%--商品详情--%><br/><spring:message code="product.add.cycle" />:</span>
				<div class="rightData">
					<ul class="rightDataImg clear">
						<li>
							<div style="width: 120px;height: 120px;"><img id="finalImg2" src="${pageContext.request.contextPath}/image/add.jpg" width="100%"></div>
							<button id="replaceImg1"></button>
						</li>
						<li>
							<div style="width: 120px;height: 120px;"><img id="finalImg3" src="${pageContext.request.contextPath}/image/add.jpg" width="100%"></div>
							<button id="replaceImg2"></button>
						</li>
						<li>
							<div style="width: 120px;height: 120px;"><img id="finalImg4" src="${pageContext.request.contextPath}/image/add.jpg" width="100%"></div>
							<button id="replaceImg3"></button>
						</li>
						<li><i class="reds"><spring:message code="product.add.cycle.message" /></i></li>
					</ul>
					<div class="finalImg234"><s class="finalImg2"></s><s class="finalImg3"></s><s class="finalImg4"></s></div>
				</div>
			</li>
			<li style="padding:10px;border-bottom:1px solid red"><i class="reds"><spring:message code="product.add.message.one" /></i></li>
		</ul>
		<ul class="dataTable">
			<li>
				<span class="leftText"><spring:message code="product.add.cost" />:</span>
				<div class="rightData">
					<ul class="clear rightDatainput">
						<li><input type="text" name="productCost" class="productCost" ><span><spring:message code="product.add.cost.message" /></span></li>
					</ul>
				</div>
			</li>
			<li>
				<span class="leftText"><spring:message code="product.add.freight" />:</span>
				<div class="rightData">
					<ul class="clear rightDatainput">
						<li><input type="text" name="productFreight" class="productFreight"><span><spring:message code="product.add.freight.message" /></span></li>
						<!-- <li><i>满：</i><input type="text"><span>包邮（RMB）</span></li> -->
					</ul>
				</div>
			</li>
			<li>
				<span class="leftText"><spring:message code="product.add.classify" />:</span>
				<div class="rightData">
					<ul class="clear rightDataSelect">
							<li>
								<select id="fave2"name="fave2"></select>
						      	<span><spring:message code="product.add.first.classify" /></span>
					     	</li>
					     	<li>
								<select id="fave3"name="fave3"></select>
						      	<span><span><spring:message code="product.add.second.classify" /></span></span>
					     	</li>
					</ul>
				</div>
			</li>
			<li style="padding:10px;border-bottom:1px solid red"><i class="reds"><spring:message code="product.add.message.one" /></i></li>
			<li>
				<span class="leftText"><spring:message code="product.add.kind.info" />:</span>
				<div class="rightData">
					<ul class="rightDatalei"></ul>
					<div class="rightDataleijia btnbtnshows"><input type="text" class="guige" placeholder = <spring:message code="product.add.kind.placeholder" />  value=""><span class="btntianjiaguige"><spring:message code="product.add.kind.name" /></span> <i style="color:red"><spring:message code="product.add.kind.eg" /></i></div>
					<button class="btns btnliebiao"><spring:message code="product.add.generate.kind" /></button>
					<p class="reds btnliebiao" style="text-align: center;"><spring:message code="product.add.kind.info.message" /></p>
				</div>
			</li>
		</ul>
		<div class="baocun">
			<ul class="btnsData clear"></ul>
			<button class="btns" style="display:none;"><spring:message code="product.add.save" /></button>
		</div>
		<div style="height:100px;"></div>
		
		<!--图片裁剪框 start-->
		<div style="display: none" class="tailoring-container">
		    <div class="black-cloth" onclick="closeTailor(this)"></div>
		    <div class="tailoring-content">
		            <div class="tailoring-content-one">
		                <label title=<spring:message code="product.add.upload.title" /> for="chooseImg" class="l-btn choose-btn">
		                    <input type="file" accept="image/jpg,image/jpeg,image/png" name="file" id="chooseImg" class="hidden" onchange="selectImg(this)">
							<spring:message code="product.add.upload.title" />
		                </label>
		                <i class="reds" style="line-height:31px;float:left;padding-left:15px;"><spring:message code="product.add.upload.message" /></i>
		                <div class="close-tailoring"  onclick="closeTailor(this)">×</div>
		            </div>
		            <div class="tailoring-content-two">
		                <div class="tailoring-box-parcel">
		                    <img id="tailoringImg">
		                </div>
		                <div class="preview-box-parcel">
		                    <p><spring:message code="product.add.image.preview" />：</p>
		                    <div class="square previewImg"></div>
		                    <div class="reds">
								<spring:message code="product.add.image.message" />
		                    </div>
		                </div>
		            </div>
		            <div class="tailoring-content-three">
		                <button class="l-btn sureCut" id="sureCut"><spring:message code="product.add.determine" /></button>
		            </div>
		        </div>
		</div>
		<!--图片裁剪框 end-->
	</body>
	
	
</html>