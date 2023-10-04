import axios from "axios";
import Swal from "sweetalert2";

const DeleteReport = (props: { reportId: number; closeModal: () => void }) => {
  const deleteReport = () => {
    axios
      .delete(`https://nubi0.com/safe/v1/safe/report/${props.reportId}`)
      .then((res) => {
        console.log(res);
        props.closeModal();
        Swal.fire({
          title: "제보 삭제 완료",
          text: "NUBIO",
        });
      });
  };
  return <button onClick={deleteReport}>삭제</button>;
};
export default DeleteReport;
