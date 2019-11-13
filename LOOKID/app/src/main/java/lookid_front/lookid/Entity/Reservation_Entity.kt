package lookid_front.lookid.Entity

import java.io.Serializable
import kotlin.collections.ArrayList

data class Reservation_Entity(
        var rv_pid: Int, //인덱스
        var r_name: String, //예약명
        var r_date: String, //예약 날짜
        var s_date: String, //사용 시작 날짜
        var e_date: String, //사용 종료 날짜
        var receipt_item: Int, //수령할 때 택배 0, 방문 1
        var return_item: Int, //반납할 때 택배 0, 방문 1
        var cost: Int, //지불 비용
        var deposit: Int, //보증금
        var wb_num: String, //운송장 번호
        var state: Int, //예약 상태 (1 입금 대기 2 예약 완료 3 배송 중 4 수령 완료 5 반납 대기 6 반납 완료 7 취소 신청 8 환불 완료)
        var group_list: ArrayList<Group_Entity>, //예약 그룹 리스트
        var user: User_Entity //예약자 정보 (!어플리케이션의 사용자 정보와 다름), var wb_num: kotlin.Int){}
) : Serializable{
    constructor() : this(0, "", "", "", "", 0, 0, 0, 0, "",0,
            arrayListOf<Group_Entity>(), User_Entity())
    constructor(resname : String, s_date : String, e_date : String, state : Int) : this(0, resname, "", s_date, e_date,
            0, 0, 0, 0, "",state, arrayListOf<Group_Entity>(), User_Entity())

    fun null_res():Boolean{
        if(r_name.isEmpty() || r_date.isEmpty() || s_date.isEmpty() || e_date.isEmpty() || cost == 0
        || deposit == 0 || group_list.isEmpty() || user.isresnull())
            return true
        for(i in 0 until group_list.size){
            if(group_list[i].isnull())
                return true
        }
        return false
    }
    fun null_res_modify():Boolean{
        if(rv_pid.toString().isNullOrEmpty() || r_name.isEmpty() || r_date.isEmpty() || s_date.isEmpty() || e_date.isEmpty() || receipt_item.toString().isEmpty() || return_item.toString().isEmpty() || cost.toString().isEmpty()
                ||deposit.toString().isEmpty() || state.toString().isEmpty() || group_list.isEmpty() || user!!.isresnull())
            return true
        return false
    }

    fun get_device_num():Int{
        var num : Int = 0
        for(i in 0 until group_list.size)
            num += group_list[i].child_list.size

        return num
    }
}

