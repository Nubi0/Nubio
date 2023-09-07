import { EnjoyHeaderWrapper } from "../../../styles/SAllCoursePage";

const EnjoyHeader = ({pageName}: PageName) => {
    const backImg = process.env.PUBLIC_URL + '/assets/back.svg';
    return(
        <EnjoyHeaderWrapper>
            <img src={backImg} alt="" />
            <div>{pageName}</div>
        </EnjoyHeaderWrapper>
    );
}

export default EnjoyHeader