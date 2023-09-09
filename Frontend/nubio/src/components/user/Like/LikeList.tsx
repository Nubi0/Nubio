import { LikeListWrapper } from '../../../styles/SLikePage';
import LikeHeader from './LikeHeader';
import PlaceList from './place/PlaceList';

const LikeList = () => {
  return (
    <LikeListWrapper>
      <LikeHeader />
      <PlaceList />
    </LikeListWrapper>
  );
};

export default LikeList;
