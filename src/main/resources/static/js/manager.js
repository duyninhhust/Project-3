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



});

function deletePhone(){
    var arrayCheckbox = [];
    $("input:checkbox[name='checkbox']:checked").each(function(){
        arrayCheckbox.push($(this).val());
    });

    if(arrayCheckbox.length > 0){
        console.log(arrayCheckbox);
        $.ajax({
            url : "/deletePhone",
            type : "delete",
            data : {
                phoneIds: arrayCheckbox
            },
            success : function a(result){
                alert("Xóa thành công");
                window.location.href= "/manage";
            }
        });
    }

}




