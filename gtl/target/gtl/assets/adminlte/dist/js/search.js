/**
 * Created by Administrator on 2016/8/4.
 */
(function(){
    var FirstBTN = $(".fa-fast-backward");
    var LasttBTN = $(".fa-fast-forward");
    var PreBTN = $(".fa-step-backward");
    var NexttBTN = $(".fa-step-forward");

    var refresh = $(".fa-refresh");

    var allpagenumshow = $("#allpagenumshow");

    var pageSearch = $(".page-search");

    pageSearch.on('change',function(){
        var pagesize = $(".page-search option:selected").text();
        var allpagenum = allpagenumshow.html();
        //每页显示条数发生改变
        $("#showpagenum").attr('value',pagesize);

        //相当于刷新，显示第一页
        $('#showpage').attr('value',1);

        //总页数改变
        //......

        //重新请求ajax显示数据
        //.....

    })



    FirstBTN.on('click',function(){
        var CurrentNum = $('#showpage').val();
        var allpagenum = allpagenumshow.html();
        //跳到第一页

        //1.显示框显示 第一页
        $('#showpage').attr('value',1);

        //请求ajax，pagenum = 1；
        ajaxsearch(url,1,callback);

        return CurrentNum = 1;
    });
    LasttBTN.on('click',function(){
        var CurrentNum = $('#showpage').val();
        var allpagenum = allpagenumshow.html();
        //跳到最后一页
        CurrentNum = allpagenum; // 总页数
        //1.显示框显示 第一页
        $('#showpage').attr('value',allpagenum);

        //请求ajax，pagenum = allpagenum；
        ajaxsearch(url,allpagenum,callback);

        return CurrentNum;
    });
    PreBTN.on('click',function(){
        var CurrentNum = $('#showpage').val();
        var allpagenum = allpagenumshow.html();
        if (CurrentNum <= 1){
            return CurrentNum;
        }else {
            //跳到前一页
            CurrentNum--;
            //1.显示框显示 第一页
            $('#showpage').attr('value',CurrentNum);

            //请求ajax，pagenum -= 1；
            ajaxsearch(url,CurrentNum,callback);

            return CurrentNum;
        }

    });
    NexttBTN.on('click',function(){
        var CurrentNum = $('#showpage').val();
        var allpagenum = allpagenumshow.html();
        //跳到下一页
        console.log(CurrentNum+'...........'+allpagenum)
        if (CurrentNum>allpagenum-1){
            return CurrentNum;
        }else{
            //1.显示框显示 第一页
            CurrentNum++;
            $('#showpage').attr('value',CurrentNum);

            //请求ajax，pagenum += 1；
            ajaxsearch(url,CurrentNum,callback);

            return CurrentNum;
        }

    });


    var ajaxsearch = function (url,pagenum,callback){
        var url = url;
        var pagenum = pagenum;
        $.ajax({
            url: url+'pagenum='+pagenum,
            success:callback
        })
    }


})()