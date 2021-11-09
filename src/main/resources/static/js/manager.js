$(document).ready(function () {
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
    checkbox.click(function () {
        if (!this.checked) {
            $("#selectAll").prop("checked", false);
        }
    });


    $("#addForm").submit(function(){
        var user = {
            name: $("#addForm input[name='name']").val(),
            image: $("#addForm input[name='image']").val(),
            price: $("#addForm input[name='price']").val(),
            quantity: $("#addForm input[name='quantity']").val(),
            introduction: $("#addForm textarea[name='introduction']").val(),
            category: {
                id: $("#addForm select[name='category']").val()
            }
        };

        $.ajax({
            type: "POST",
            url: "products",
            data: JSON.stringify(user),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(msg) {
                alert('Thêm sản phẩm thành công');
                window.location.href = '/manage';
            }
        });

        return false;
    });

    $("#editForm").submit(function(){
        debugger;
        var user = {
            name: $("#editForm input[name='name']").val(),
            image: $("#editForm input[name='image']").val(),
            price: $("#editForm input[name='price']").val(),
            quantity: $("#editForm input[name='quantity']").val(),
            introduction: $("#editForm textarea[name='introduction']").val(),
            category: {
                id: $("#editForm select[name='category']").val()
            }
        };

        $.ajax({
            type: "POST",
            url: "/products/" + $("#editForm input[name='id']").val(),
            data: JSON.stringify(user),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(msg) {
                alert('Sửa thông tin sản phẩm thành công');
                window.location.href = '/manage';
            }
        });

        return false;
    });
});


