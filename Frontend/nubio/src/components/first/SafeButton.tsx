import { useNavigate } from "react-router-dom";
import { SafeButtonWrapper } from "../../styles/SHomePage";
import Swal from "sweetalert2";

const SafeButton = ({isLogin}: {isLogin: any}) => {
  const navigate = useNavigate();
  const handleClick = () => {
    if(isLogin) {
      navigate('/safe');
    } else {
      Swal.fire({
        title: '로그인이 필요합니다.',
        icon: 'info',
        text: 'NUBIO'
      })
    }
  }
  return (
    <SafeButtonWrapper  
      onClick={handleClick}
    >
      <img src={process.env.PUBLIC_URL + '/assets/safe.png'} alt="" />
      <button>
        안전 모드
      </button>
    </SafeButtonWrapper>
  );
};

export default SafeButton;
