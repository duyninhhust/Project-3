package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.hust.project3.model.Bill;
import vn.edu.hust.project3.model.BillPhone;
import vn.edu.hust.project3.service.BillPhoneService;
import vn.edu.hust.project3.service.BillService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ManageBillResource {

    private final BillService billService;

    private final BillPhoneService billPhoneService;

    @GetMapping(value = "/manage-bill/{pageNo}")
    public String ManagerBill(Model model, @PathVariable(name = "pageNo") int pageNo,
                              @RequestParam(name = "sortField") String sortField,
                              @RequestParam(name = "sortDir") String sortDir){
        int pageSize = 3;
        int pageId = 2;

        Page<Bill> billPage = billService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Bill> bills = billPage.getContent();
        List<String> statuses = new ArrayList<>();

        model.addAttribute("bills", bills);
        model.addAttribute("statuses", statuses);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageId", pageId);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", billPage.getTotalPages());
        model.addAttribute("totalItems", billPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "ManagerBill";
    }

    @GetMapping(value = "/manage-bill/view")
    public String ManagerBill(@RequestParam int id, Model model)  {
        List<BillPhone> billDetails = billPhoneService.getAllBillPhoneByBillId(id);
        Bill bill = billService.getBillById(id);
        //Status đơn hàng
        String status = "";
        switch (bill.getStatus()){
            case 1:{
                status = "Waiting for processing";
                break;
            }
            case 2:{
                status = "Confirmed";
                break;
            }
            case 3:{
                status = "Cancel delivery";
                break;
            }
        }
        //Lấy tổng tiền
        Double totalMoney = billPhoneService.totalMoneyInBill(id);
        System.out.println(totalMoney);

        model.addAttribute("status", status);
        model.addAttribute("totalMoney", totalMoney);
        model.addAttribute("bill", bill);
        model.addAttribute("billDetail", billDetails);

        return "ManageBillDetail";
    }

    //Confirm or cancel đơn hàng
    @GetMapping(value = "/manage-bill/confirm")
    public String ConfirmBill(@RequestParam int id) {
        billService.confirmBill(id);
        return "redirect:/manage-bill/view?id="+id;
    }

    @GetMapping(value = "/manage-bill/cancel")
    public String CancelBill(@RequestParam int id) {
        billService.cancelBill(id);
        return "redirect:/manage-bill/view?id="+id;
    }

    @GetMapping(value = "/manage-bill/delete")
    String deleteBill(@RequestParam int id){
        billPhoneService.deleteBillPhoneByBillId(id);
        billService.deleteBill(id);
        return "redirect:/manage-bill/1?sortField=id&sortDir=asc";
    }

}
