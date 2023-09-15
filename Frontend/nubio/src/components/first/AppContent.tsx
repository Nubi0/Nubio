import { AppContentWrapper, Discuss } from "../../styles/SHomePage";

const AppContent = () => {
    const enjoyimg = ['eating', 'cafe', 'movie', 'boardgame', 'walking']
    const disasterimg = ['snowFlood', 'heavyRain', 'hot', 'fire', 'earthquake', 'terror']
    return(
        <AppContentWrapper>
            <Discuss>
                데이트를 하거나 친구들과<br/> 놀러 가기전 맞춤형 코스를<br/> 추천받아보세요!
                <div>
                    {enjoyimg.map((name) => {
                        return(
                            <img src={process.env.PUBLIC_URL + `/assets/enjoy/${name}.png`} alt="" />
                        )
                    })}
                </div>
            </Discuss>
            <Discuss>
                재난문자를 받았을 때 안전<br/> 솔루션을 제공받아보세요!
                <div>
                    {disasterimg.map((name) => {
                        return(
                            <img src={process.env.PUBLIC_URL + `/assets/disaster/${name}.svg`} alt="" />
                        )
                    })}
                </div>
            </Discuss>
        </AppContentWrapper>
    );
}

export default AppContent;