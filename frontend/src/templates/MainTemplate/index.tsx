import { Footer } from '../../components/Footer';
import { Navbar } from '../../components/Navbar';

type MainTemplateProps = {
  children: React.ReactNode;
};

export function MainTemplate({ children }: MainTemplateProps) {
  return (
    <>
      <Navbar />
      {children}
      <Footer />
    </>
  );
}
