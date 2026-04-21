import type { Skill } from '../../models/SkillModel';

type TechnologiesCardProps = {
  skill: Skill;
};

export function TechnologiesCard({ skill }: TechnologiesCardProps) {
  return (
    <>
      <div className='glass p-8 rounded-xl flex flex-col items-center gap-6 min-h-[200px] min-w-[300px]'>
        <h3 className='uppercase text-text-dim text-xs'>{skill.category}</h3>
        <div
          className='w-16 h-16 flex items-center justify-center text-primary fill-current'
          dangerouslySetInnerHTML={{ __html: skill.imgUrl }}
        />
        <div className='px-3 py-1 bg-primary/10 border border-primary/20 rounded-md text-xs text-primary font-bold'>
          {skill.name}
        </div>
      </div>
    </>
  );
}
