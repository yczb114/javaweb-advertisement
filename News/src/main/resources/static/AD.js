document.addEventListener("DOMContentLoaded", function () {
    var ad = document.getElementById('ad');
    var closeBtn = document.getElementById('close');
    //表单名
    var forms = document.querySelectorAll('form[name="commodity"]');
    var tag='test';
    closeBtn.onclick = function () {
        ad.style.display = 'none';
    };
    console.log(1)
    forms.forEach(function(form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            //改为tag名
            tag=document.querySelector('input[name="adtag"]').value;
            //传tag
            fetch(`http://116.62.49.213:8080/ADManageMent-1.0-SNAPSHOT/AdResponse-servlet?tag=${tag}`,{
                mode:'cors',
                credentials:'include',
            })
                .then(function(response) {
                    if (!response.ok) {
                        throw new Error('返回出错' + response.statusText);
                    }
                    return response.json();
                })
                .then(function(data) {
                    var img=document.getElementById("adImg");
                    const base64Image = data.base64photo;
                    img.src=`data:image/jpeg;base64,${base64Image}`;
                    img.addEventListener('click',function(){
                        var id=data.adid;
                        //根据点击广告的id 发给广告商
                        ////加上username
                        fetch(`http://116.62.49.213:8080/ADManageMent-1.0-SNAPSHOT/ClickRate-servlet?adid=${id}`,{
                            mode:'cors',
                        }).then(function(response) {
                            if (!response.ok) {
                                throw new Error('返回出错' + response.statusText);
                            }
                        })
                    });
                })
                .catch(function(error) {
                    console.error('fetch出错:', error);
                });
            form.submit();
        });
    });
    //第一次进给的广告
    fetch(`http://116.62.49.213:8080/ADManageMent-1.0-SNAPSHOT/AdResponse-servlet?tag=${tag}`,{
        mode:'cors',
        credentials:'include',
    })
        .then(function(response) {
            if (!response.ok) {
                throw new Error('返回出错' + response.statusText);
            }
            return response.json();
        })
        .then(function(data) {
            var img=document.getElementById("adImg");
            const base64Image = data.base64photo;
            img.src=`data:image/jpeg;base64,${base64Image}`;
            img.addEventListener('click',function(){
                var id=data.adid;
                fetch(`http://116.62.49.213:8080/ADManageMent-1.0-SNAPSHOT/ClickRate-servlet?adid=${id}&username=shop`,{
                    mode:'cors',
                }).then(function(response) {
                    if (!response.ok) {
                        throw new Error('返回出错' + response.statusText);
                    }
                })
                window.location.href = data.adurl;
            });
        })
        .catch(function(error) {
            console.error('fetch出错:', error);
        });

});