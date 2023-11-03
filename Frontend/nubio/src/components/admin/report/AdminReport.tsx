import { useSelector } from "react-redux";
import ReportItem from "./ReportItem"
const AdminReport = ()=>{
    const reportList = useSelector(
        (state: { safe: { reportList: Array<any> } }) => state.safe.reportList,
      );
    console.log(reportList);
    return(
    <>
        {reportList.map((reportItem : ReportItem)=>{
            <ReportItem value={reportItem}/>
        }) }
    </>
    )
}

export default AdminReport