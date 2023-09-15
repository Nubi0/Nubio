import { useNavigate } from "react-router-dom";
import { EnjoyHeaderWrapper } from "../../../styles/SAllCoursePage";

const EnjoyHeader = ({pageName}: PageName) => {
    const navigate = useNavigate();
    const backImg = process.env.PUBLIC_URL + '/assets/back.svg';
    return(
        <EnjoyHeaderWrapper>
            <img src={backImg} alt="" onClick={() => navigate(-1)} />
            <div>{pageName}</div>
        </EnjoyHeaderWrapper>
    );
}

export default EnjoyHeader