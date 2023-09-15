import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';
import { PinWrapper, CoursePinWrapper  } from '../../../../styles/SCourseSelectPage';
import CoursePin from './CoursePin';

const CoursePinList = ({positions}: any) => {
    
    const handleChange = (result: any) => {
        console.log('드래그')
    }
    return(
        <DragDropContext onDragEnd={handleChange}>
            <PinWrapper>
                <Droppable droppableId='box' direction='horizontal'>
                    {(provided) => (
                        <div ref={provided.innerRef} {...provided.droppableProps} style={{ display: 'flex', overflowX: 'auto' }}>
                            {positions.map((value: any, index: number) => {
                                return(
                                <Draggable draggableId={index.toString()} index={index} key={index}>
                                    {(provided, snapshot) => (
                                        <CoursePinWrapper 
                                            ref={provided.innerRef}
                                            {...provided.draggableProps}
                                            {...provided.dragHandleProps}    
                                            key={index}
                                        >
                                            <CoursePin key={index} value={value} />
                                        </CoursePinWrapper>
                                    )}
                                </Draggable>
                                );
                            })}
                            {provided.placeholder}
                        </div>
                    )}
                </Droppable>
            </PinWrapper>
        </DragDropContext>
    );
}

export default CoursePinList;