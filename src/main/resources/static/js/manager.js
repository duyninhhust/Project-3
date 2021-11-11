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
    checkbox.click(function (){
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


        console.log(arrayCheckbox);
        $.ajax({
            url : "/deletePhone",
            type : "delete",
            data : {
                phoneIds: arrayCheckbox
            },
            success : function a(result){

                window.location.href= "/manage";
            }
        });


}

function openDeleteModal(){
    var arrayCheckbox = [];
    $("input:checkbox[name='checkbox']:checked").each(function(){
        arrayCheckbox.push($(this).val());
    });
    if(arrayCheckbox.length > 0) {
        var container = $("body");
        var button = document.createElement('button');
        button.type = 'button';
        button.style.display = 'none';
        button.setAttribute('data-toggle', 'modal');
        button.setAttribute('data-target', '#deleteEmployeeModal');
        container.append(button);
        button.click();
    }
}



