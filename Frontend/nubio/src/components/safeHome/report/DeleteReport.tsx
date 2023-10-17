import axios from "axios";
import Swal from "sweetalert2";

const DeleteReport = (props: { reportId: number; closeModal: () => void }) => {
  const deleteReport = () => {
    axios
      .delete(
        `${process.env.REACT_APP_SERVER_URL}/safe/v1/safe/report/${props.reportId}`,
      )
      .then(() => {
        props.closeModal();
        Swal.fire({
          title: "제보 삭제 완료",
          text: "NUBIO",
        }).then(() => {
          window.location.reload();
        });
      });
  };
  return <button onClick={deleteReport}>삭제</button>;
};
export default DeleteReport;
