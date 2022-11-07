$(document).ready(function () {
    $('#fileImage').change(function () {
        showImageThumbnail(this);
    })
    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click(function () {
        if (this.checked) {
            checkbox.each(function () {
                this.checked = true;
            });
        } else {
            checkbox.each(function () {
                this.checked = false;
            });
        }
    });
    checkbox.click(function (){
        if (!this.checked) {
            $("#selectAll").prop("checked", false);
        }
    });

});

function deleteManyPhone(id){
    debugger;
    if(id == null){
        var arrayCheckbox = [];
        $("input:checkbox[name='checkbox']:checked").each(function(){
            arrayCheckbox.push($(this).val());
        });
        $.ajax({
            url : "/delete-phone",
            type : "delete",
            data : {
                phoneIds: arrayCheckbox
            },
            success : function a(result){
                if(result == "00"){
                    Swal.fire({
                        title: 'Thông báo',
                        text: 'Xóa thành công',
                        icon: 'success',
                        confirmButtonText: 'OK'
                    }).then(function () {
                        window.location.href= "/manage-phone/1?sortField=id&sortDir=asc";
                    })
                } else {
                    Swal.fire({
                        title: 'Thông báo',
                        text: 'Có lỗi khi xóa ',
                        icon: 'error',
                        confirmButtonText: 'OK'
                    }).then(function () {
                        window.location.href= "/manage-phone/1?sortField=id&sortDir=asc";
                    })
                }

            }
        });
    } else {
        console.log("id", id);
        $.ajax({
            url : "/delete-one-phone",
            type : "delete",
            data : {
                id: id
            },
            success : function a(result){
                if(result == "00"){
                    Swal.fire({
                        title: 'Thông báo',
                        text: 'Xóa thành công',
                        icon: 'success',
                        showCancelButton: false,
                        showConfirmButton: false
                    }).then(
                        setTimeout(function() {
                            Swal.close();
                            window.location.href= "/manage-phone/1?sortField=id&sortDir=asc";
                        }, 1000)
                    )
                } else {
                    Swal.fire({
                        title: 'Thông báo',
                        text: 'Có lỗi khi xóa ',
                        icon: 'error',
                        showCancelButton: false,
                        showConfirmButton: false
                    }).then(function () {
                        setTimeout(function() {

                            window.location.href= "/manage-phone/1?sortField=id&sortDir=asc";
                        }, 1000);
                    })
                }

            }
        });
    }


}

function deleteManyUser(id){
    debugger;
    if(id == null){
        var arrayCheckbox = [];
        $("input:checkbox[name='checkbox']:checked").each(function(){
            arrayCheckbox.push($(this).val());
        });
        $.ajax({
            url : "/delete-user",
            type : "delete",
            data : {
                phoneIds: arrayCheckbox
            },
            success : function a(result){
                if(result == "00"){
                    Swal.fire({
                        title: 'Thông báo',
                        text: 'Xóa thành công',
                        icon: 'success',
                        confirmButtonText: 'OK'
                    }).then(function () {
                        window.location.href= "/manage-user/1?sortField=id&sortDir=asc";
                    })
                } else {
                    Swal.fire({
                        title: 'Thông báo',
                        text: 'Có lỗi khi xóa ',
                        icon: 'error',
                        confirmButtonText: 'OK'
                    }).then(function () {
                        window.location.href= "/manage-user/1?sortField=id&sortDir=asc";
                    })
                }

            }
        });
    } else {
        console.log("id", id);
        $.ajax({
            url : "/delete-one-user",
            type : "delete",
            data : {
                id: id
            },
            success : function a(result){
                if(result == "00"){
                    Swal.fire({
                        title: 'Thông báo',
                        text: 'Xóa thành công',
                        icon: 'success',
                        showCancelButton: false,
                        showConfirmButton: false
                    }).then(
                        setTimeout(function() {
                            Swal.close();
                            window.location.href= "/manage-user/1?sortField=id&sortDir=asc";
                        }, 1000)
                    )
                } else {
                    Swal.fire({
                        title: 'Thông báo',
                        text: 'Có lỗi khi xóa ',
                        icon: 'error',
                        showCancelButton: false,
                        showConfirmButton: false
                    }).then(function () {
                        setTimeout(function() {

                            window.location.href= "/manage-user/1?sortField=id&sortDir=asc";
                        }, 1000);
                    })
                }

            }
        });
    }


}

function openDeleteModal(id){
    var arrayCheckbox = [];
    $("input:checkbox[name='checkbox']:checked").each(function(){
        arrayCheckbox.push($(this).val());
    });
    if(arrayCheckbox.length > 0 || id != null) {
        Swal.fire({
            title: 'Xác nhận xóa',
            text: 'Bạn có chắc chắn muốn xóa?',
            icon: 'warning',
            cancelButtonText: "CANCEL",
            confirmButtonText: 'OK',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
        }).then(function (result) {
            if (result.value) {
                deleteManyPhone(id);
            }
        })
    }

}

function openDeleteUserModal(id){
    var arrayCheckbox = [];
    $("input:checkbox[name='checkbox']:checked").each(function(){
        arrayCheckbox.push($(this).val());
    });
    if(arrayCheckbox.length > 0 || id != null) {
        Swal.fire({
            title: 'Xác nhận xóa',
            text: 'Bạn có chắc chắn muốn xóa?',
            icon: 'warning',
            cancelButtonText: "CANCEL",
            confirmButtonText: 'OK',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
        }).then(function (result) {
            if (result.value) {
                deleteManyUser(id);
            }
        })
    }

}

function showImageThumbnail(fileInput) {
    file = fileInput.files[0]
    reader = new FileReader();
    reader.onload = function (e) {
        $('#thumbnail').attr('src', e.target.result)
    };
    reader.readAsDataURL(file);
}




