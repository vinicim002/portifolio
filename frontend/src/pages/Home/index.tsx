import { AboutMe } from '../../components/AboutMe';
import { Contact } from '../../components/Contact';
import { Hero } from '../../components/Hero';
import { Projects } from '../../components/Projects';
import { Technologies } from '../../components/Technologies';

export function Home() {
  return (
    <>
      <section id='home'>
        <Hero />
      </section>
      <section id='about'>
        <AboutMe />
      </section>
      <section id='technologies'>
        <Technologies />
      </section>
      <section id='projects'>
        <Projects />
      </section>
      <section id='contact'>
        <Contact />
      </section>
    </>
  );
}
