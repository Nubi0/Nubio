import styled from 'styled-components';
import SafeButton from './firsthome/SafeButton';
import EnjoyButton from './firsthome/EnjoyButton';

const FirstHome = () => {
    return(
        <FirstHomeWrapper>
            <SafeButton />
            <EnjoyButton />
        </FirstHomeWrapper>
    );
}

export default FirstHome;

const FirstHomeWrapper = styled.div``;