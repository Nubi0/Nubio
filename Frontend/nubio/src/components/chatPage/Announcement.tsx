import React, {useState}from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import { faChevronDown, faChevronUp } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

interface NoticeWrapperProps {
    show: boolean;
  }
  
 
  const NoticeWrapper = styled.div<NoticeWrapperProps>`
  background-color: #fef0cc;
  overflow: hidden;
  padding: 10px;
  margin: 10px 0;
  border-radius: 5px;
  color: #5a5a5a;
  font-size: 14px;
  height: ${props => props.show ? 'auto' : '0'}; 
  visibility: ${props => props.show ? 'visible' : 'hidden'};
`;

const ToggleButton = styled.button`
  background: transparent;
  border: none;
  padding: 5px 10px;
  margin-bottom: 10px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
`;


const NoticeTitle = styled.h3`
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 5px;
`;

const NoticeContent = styled.p`
  margin: 0;
`;

const Announcement = () => {
    const [showNotice, setShowNotice] = useState(true);
    const locationState = useSelector((state:any) => state.location);
  
    return (
      <>
        <ToggleButton onClick={() => setShowNotice(!showNotice)}>
          <FontAwesomeIcon icon={showNotice ? faChevronDown: faChevronUp} />
        </ToggleButton>
        <NoticeWrapper show={showNotice}>
          <NoticeTitle>긴급 공지</NoticeTitle>
          <NoticeContent>
            {locationState.chatClient.emergency_message.message}
          </NoticeContent>
          <NoticeTitle>최신 제보 사항</NoticeTitle>
          <NoticeContent>
            {locationState.chatClient.report.content}
          </NoticeContent>
        </NoticeWrapper>
      </>
    );
  };
  
  export default Announcement;