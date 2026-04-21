import { MainRouter } from './routers/MainRouter';
import './styles/global.css';
import './styles/theme.css';
import { MainTemplate } from './templates/MainTemplate';

export function App() {
  return (
    <>
      <MainTemplate>
        <MainRouter/>
      </MainTemplate>
    </>
  );
}
