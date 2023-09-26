import { CategoryWrapper } from "../../../styles/SAllCoursePage";

const Category = ({active, handleButton} : {active: any, handleButton: any}) => {
    return(
        <CategoryWrapper>
            <div onClick={() => handleButton('all')} className={active.all ? 'active' : ''}>전체</div>
            <div onClick={() => handleButton('popular')} className={active.popular ? 'active' : ''}>인기</div>
        </CategoryWrapper>
    );
}

export default Category;