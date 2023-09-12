import { useNavigate } from "react-router-dom";
import { EnjoyButtonWrapper } from "../../styles/SHomePage";

const EnjoyButton = () => {
  const navigate = useNavigate();
  return (
    <EnjoyButtonWrapper>
      <button
        onClick={() => {
          navigate("/enjoy");
        }}
      >
        흥미
      </button>
    </EnjoyButtonWrapper>
  );
};

export default EnjoyButton;
