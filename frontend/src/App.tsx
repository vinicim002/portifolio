import { Hero } from './components/Hero';
import { Navbar } from './components/Navbar';
import './styles/global.css';
import './styles/theme.css';

export function App() {
  return (
    <>
      <Navbar></Navbar>
      <Hero></Hero>
      <h1>Hello World</h1>
    </>
  );
}
