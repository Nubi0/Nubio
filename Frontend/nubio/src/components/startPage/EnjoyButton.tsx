import { useNavigate } from "react-router-dom";
import { EnjoyButtonWrapper } from "../../styles/SHomePage";
import Swal from "sweetalert2";

const EnjoyButton = ({
  isTaste,
  isLogin,
}: {
  isTaste: boolean;
  isLogin: string | null;
}) => {
  const navigate = useNavigate();
  const handleClick = () => {
    if (isTaste && isLogin) {
      navigate("/enjoy");
    } else if (!isLogin) {
      Swal.fire({
        title: "로그인이 필요합니다.",
        icon: "info",
        text: "NUBIO",
      });
    } else {
      Swal.fire({
        title: "취향을 설정해주세요",
        icon: "info",
        text: "NUBIO",
      });
    }
  };
  return (
    <EnjoyButtonWrapper onClick={handleClick}>
      <img
        src={process.env.PUBLIC_URL + "/assets/startPage/enjoy.png"}
        alt=""
      />
      <button>흥미 모드</button>
    </EnjoyButtonWrapper>
  );
};

export default EnjoyButton;
